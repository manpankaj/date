package date.jalaunup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import date.jalaunup.Objects.Employment;
import date.jalaunup.R;
import date.jalaunup.ViewWorkerEmploymentDetailActivity;

public class EmploymentAdapter extends RecyclerView.Adapter<EmploymentAdapter.ViewHolder>{
    public Employment[] listdata;
    private Context mContext;
    public EmploymentAdapter(Context context, Employment[] listdata)
    {
        this.listdata = listdata;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_employment, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Employment myListData = listdata[position];
        holder.textViewId.setText(" Id : " +listdata[position].getProjectId());
        holder.textViewName.setText(" Project Name : " + listdata[position].getProjectName());
        holder.textViewAddress.setText(" Address : " + listdata[position].getProjectAddress());
        holder.textViewTehsil.setText(" Tehsil : " + listdata[position].getProjectTehsil());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewWorkerEmploymentDetailActivity.class);
                intent.putExtra("ProjectId", myListData.getProjectId());
                intent.putExtra("ProjectMobile", myListData.getProjectMobile());
                intent.putExtra("ProjectName", myListData.getProjectName());
                intent.putExtra("ProjectAddress", myListData.getProjectAddress());
                intent.putExtra("ProjectTehsil", myListData.getProjectTehsil());
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
        public TextView textViewId, textViewName, textViewAddress, textViewTehsil;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewProjectId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewProjectName);
            this.textViewAddress = (TextView) itemView.findViewById(R.id.textProjectAddress);
            this.textViewTehsil = (TextView) itemView.findViewById(R.id.textProjectTehsil);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            Context context = itemView.getContext();
        }
    }
}

