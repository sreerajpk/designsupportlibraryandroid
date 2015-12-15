package com.example.user.designsupportlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 7/6/15.
 */
public class Adapter extends BaseAdapter {
    private Context context;
    private List<Conversation> conversationList;
    private Conversation removedConversation;
    private int removedPosition;
    private int lastPosition = -1;

    public Adapter(Context context, List<Conversation> conversationList) {
        this.context = context;
        this.conversationList = conversationList;
    }

    public void removeConversation(int i) {
        removedConversation = conversationList.get(i);
        removedPosition = i;
        conversationList.remove(i);
        notifyDataSetChanged();
    }

    public void addRemovedConversationAgain() {
        conversationList.add(removedPosition, removedConversation);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public Object getItem(int i) {
        return conversationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_layout, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.lastMessage = (TextView) convertView.findViewById(R.id.lastMessage);
            holder.profPic = (ImageView) convertView.findViewById(R.id.profPic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(conversationList.get(position).getName());
        holder.lastMessage.setText(conversationList.get(position).getLastMessage());


       /* Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;*/
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale);
        convertView.startAnimation(animation);

        return convertView;
    }

    private class ViewHolder {
        private ImageView profPic;
        private TextView name;
        private TextView lastMessage;
    }
}
