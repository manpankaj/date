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
import date.jalaunup.ViewRegisteredEmployerDetailActivity;

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
        holder.textViewId.setText("  Id :" +listdata[position].getEmployerId());
        holder.textViewName.setText("  Name : " + listdata[position].getEmployerName());
        holder.textViewMobileNo.setText("  Mobile No : " + listdata[position].getEmployerMobileNo());
        holder.textViewAddress.setText("  Address : " + listdata[position].getEmployerAddress());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewRegisteredEmployerDetailActivity.class);
                intent.putExtra("EmployerId", myListData.getEmployerId());
                intent.putExtra("EmployerName", myListData.getEmployerName());
                intent.putExtra("EmployerMobileNo", myListData.getEmployerMobileNo());
                intent.putExtra("EmployerAddress", myListData.getEmployerAddress());
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
        public TextView textViewId, textViewName, textViewMobileNo, textViewAddress;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewEmployerId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewEmployerName);
            this.textViewMobileNo = (TextView) itemView.findViewById(R.id.textEmployerMobileNo);
            this.textViewAddress = (TextView) itemView.findViewById(R.id.textEmployerAddress);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            Context context = itemView.getContext();
        }
    }
}

