package date.jalaunup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import date.jalaunup.Objects.AdminSearchEmployerWork;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredEmployerWorkDetailActivity;

public class AdminSearchEmployerWorkAdapter extends RecyclerView.Adapter<AdminSearchEmployerWorkAdapter.ViewHolder>{
    public AdminSearchEmployerWork[] listdata;
    private Context mContext;
    public AdminSearchEmployerWorkAdapter(Context context, AdminSearchEmployerWork[] listdata)
    {
        this.listdata = listdata;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_admin_employer_work, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final AdminSearchEmployerWork myListData = listdata[position];
        holder.textViewEmployerWorkId.setText("Project Id: " +listdata[position].getProjectId());
        holder.textViewEmployerWorkField.setText("Project Field: " + listdata[position].getProjectField());
        holder.textEmployerProjectName.setText("Project Name: " + listdata[position].getProjectName());
        holder.textEmployerWorkAddress.setText("Project Address: " + listdata[position].getProjectAddress());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewRegisteredEmployerWorkDetailActivity.class);
                intent.putExtra("ProjectId", myListData.getProjectId());
                intent.putExtra("ProjectField", myListData.getProjectField());
                intent.putExtra("ProjectName", myListData.getProjectName());
                intent.putExtra("ProjectAddress", myListData.getProjectAddress());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listdata.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewEmployerWorkId, textViewEmployerWorkField, textEmployerProjectName, textEmployerWorkAddress;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.textViewEmployerWorkId = (TextView) itemView.findViewById(R.id.textViewEmployerWorkId);
            this.textViewEmployerWorkField = (TextView) itemView.findViewById(R.id.textViewEmployerWorkField);
            this.textEmployerProjectName = (TextView) itemView.findViewById(R.id.textEmployerProjectName);
            this.textEmployerWorkAddress = (TextView) itemView.findViewById(R.id.textEmployerWorkAddress);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            Context context = itemView.getContext();
        }
    }
}

