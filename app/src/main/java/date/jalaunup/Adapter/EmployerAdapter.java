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

import androidx.recyclerview.widget.RecyclerView;

import date.jalaunup.Objects.Employer;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredEmployerDetailActivity;
import date.jalaunup.ViewRegisteredWorkerDetailActivity;


public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.ViewHolder>{
    public Employer[] listdata;
    private Context mContext;
    private String ComplainType;
    private String RoleId;

    // RecyclerView recyclerView;
    public EmployerAdapter(Context context, Employer[] listdata, String listType, String roleId)
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
        View listItem= layoutInflater.inflate(R.layout.list_item_employer, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Employer myListData = listdata[position];

        holder.textViewId.setText(listdata[position].getEmployerId());
        holder.textViewName.setText("Name : " + listdata[position].getEmployerName());
        holder.textViewEmail.setText("Email : " + listdata[position].getEmployerEmail());
        holder.textViewMobileNo.setText("MobileNo : " + listdata[position].getEmployerMobileNo());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

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


            this.textViewId = (TextView) itemView.findViewById(R.id.textViewEmployerId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewEmployerName);
            this.textViewEmail = (TextView) itemView.findViewById(R.id.textEmployerEmail);
            this.textViewMobileNo = (TextView) itemView.findViewById(R.id.textEmployerMobileNo);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);


            Context context = itemView.getContext();

        }
    }


}

