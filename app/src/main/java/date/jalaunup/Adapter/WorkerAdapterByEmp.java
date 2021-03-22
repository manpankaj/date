package date.jalaunup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import date.jalaunup.Objects.WorkerBycat;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredWorkerDetailActivity;

public class WorkerAdapterByEmp extends RecyclerView.Adapter<WorkerAdapterByEmp.ViewHolder>{
    public WorkerBycat[] listdata;
    private Context mContext;
    public WorkerAdapterByEmp(Context context, WorkerBycat[] listdata)
    {
        this.listdata = listdata;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_worker_bycat, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final WorkerBycat myListData = listdata[position];
        holder.textViewId.setText("  Id: " +listdata[position].getWorkerId());
        holder.textViewName.setText("  Name: " + listdata[position].getWorkerName());
        holder.textViewMobileNo.setText("  Mobile No: " + listdata[position].getWorkerMobileNo());
        holder.textViewSubcat.setText("  Experience Category: " + listdata[position].getWorkerSubcat());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewRegisteredWorkerDetailActivity.class);
                intent.putExtra("WorkerId", myListData.getWorkerId());
                intent.putExtra("WorkerName", myListData.getWorkerName());
                intent.putExtra("WorkerMobileNo", myListData.getWorkerMobileNo());
                intent.putExtra("WorkerSubcat", myListData.getWorkerSubcat());
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
        public TextView textViewId, textViewName, textViewMobileNo, textViewSubcat;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewWorkerId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewWorkerName);
            this.textViewMobileNo = (TextView) itemView.findViewById(R.id.textWorkerMobileNo);
            this.textViewSubcat = (TextView) itemView.findViewById(R.id.textWorkerSubcat);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            Context context = itemView.getContext();
        }
    }
}

