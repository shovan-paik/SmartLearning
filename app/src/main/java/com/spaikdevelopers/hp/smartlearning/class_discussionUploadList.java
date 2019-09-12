package com.spaikdevelopers.hp.smartlearning;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class class_discussionUploadList extends ArrayAdapter<class_discussionUpload> {
    private Activity contex;
    private List<class_discussionUpload> discussionUploadList;

    public class_discussionUploadList(Activity contex,List<class_discussionUpload> discussionUploadList){
        super(contex,R.layout.listview_discussion_upload,discussionUploadList);
        this.contex= contex;
        this.discussionUploadList = discussionUploadList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = contex.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.listview_discussion_upload,null,true);

        TextView textViewTitle = ListViewItem.findViewById(R.id.textView_titleID);
        TextView textViewDiscussion = ListViewItem.findViewById(R.id.textView_discussionID);

        class_discussionUpload classDiscussionUpload = discussionUploadList.get(position);

        textViewTitle.setText(classDiscussionUpload.getDiscussionTitle());
        textViewDiscussion.setText(classDiscussionUpload.getDiscussionView());

        return ListViewItem;
    }
}
