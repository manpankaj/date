package date.jalaunup.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import date.jalaunup.Objects.Worker;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredWorkerDetailActivity;

import androidx.recyclerview.widget.RecyclerView;



public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder>{
    public Worker[] listdata;
    private Context mContext;
    private String ComplainType;
    private String RoleId;

    // RecyclerView recyclerView;
    public WorkerAdapter(Context context, Worker[] listdata, String listType, String roleId)
    {
        this.listdata = listdata;
        mContext = context;
        ComplainType =listType;
        RoleId=roleId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Worker myListData = listdata[position];

        holder.textViewId.setText(listdata[position].getWorkerId());
        holder.textViewName.setText("Name : " + listdata[position].getWorkerName());
        holder.textViewEmail.setText("Email : " + listdata[position].getWorkerEmail());
        holder.textViewMobileNo.setText("MobileNo : " + listdata[position].getWorkerMobileNo());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                //Toast.makeText(mContext,"Role Id : "+RoleId, Toast.LENGTH_LONG).show();
                Toast.makeText(mContext,"Worker Id : "+myListData.getWorkerId(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, ViewRegisteredWorkerDetailActivity.class);
                intent.putExtra("WorkerId", myListData.getWorkerId());
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
        public ImageView imageView;
        public TextView textViewId, textViewName, textViewEmail, textViewMobileNo;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);


            this.textViewId = (TextView) itemView.findViewById(R.id.textViewWorkerId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewWorkerName);
            this.textViewEmail = (TextView) itemView.findViewById(R.id.textWorkerEmail);
            this.textViewMobileNo = (TextView) itemView.findViewById(R.id.textWorkerMobileNo);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);


            Context context = itemView.getContext();

        }
    }


}

