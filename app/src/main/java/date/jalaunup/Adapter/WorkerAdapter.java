package date.jalaunup.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import date.jalaunup.Objects.Worker;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredWorkerDetailActivity;
import androidx.recyclerview.widget.RecyclerView;
public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder>{
    public Worker[] listdata;
    private Context mContext;
    public WorkerAdapter(Context context, Worker[] listdata)
    {
        this.listdata = listdata;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_worker, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Worker myListData = listdata[position];
        holder.textViewId.setText("Worker Id: " +listdata[position].getWorkerId());
        holder.textViewName.setText("Worker Name: " + listdata[position].getWorkerName());
        holder.textViewMobileNo.setText("Worker Mobile No: " + listdata[position].getWorkerMobileNo());
        holder.textViewEmail.setText("Worker Email Id: " + listdata[position].getWorkerEmail());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewRegisteredWorkerDetailActivity.class);
                intent.putExtra("WorkerId", myListData.getWorkerId());
                intent.putExtra("WorkerName", myListData.getWorkerName());
                intent.putExtra("WorkerMobileNo", myListData.getWorkerMobileNo());
                intent.putExtra("WorkerEmail", myListData.getWorkerEmail());
                intent.putExtra("WorkerAge", myListData.getWorkerAge());
                intent.putExtra("WorkerSex", myListData.getWorkerSex());
                intent.putExtra("WorkerField", myListData.getWorkerField());
                intent.putExtra("WorkerWork", myListData.getWorkerWork());
                intent.putExtra("WorkerExp", myListData.getWorkerExp());
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

