package date.jalaunup.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import date.jalaunup.Objects.Employer;
import date.jalaunup.R;
import date.jalaunup.ViewRegisteredEmployerDetailActivity;
import androidx.recyclerview.widget.RecyclerView;
public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.ViewHolder>{
    public Employer[] listdata;
    private Context mContext;
    public EmployerAdapter(Context context, Employer[] listdata)
    {
        this.listdata = listdata;
        mContext = context;
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
        holder.textViewId.setText(" Employer Id :" +listdata[position].getEmployerId());
        holder.textViewName.setText("Employer Name : " + listdata[position].getEmployerName());
        holder.textViewMobileNo.setText("Employer Mobile No : " + listdata[position].getEmployerMobileNo());
        holder.textViewEmail.setText("Employer Email Id : " + listdata[position].getEmployerEmail());
        //holder.textViewAddress.setText("Email : " + listdata[position].getEmployerAddress());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, ViewRegisteredEmployerDetailActivity.class);
                intent.putExtra("EmployerId", myListData.getEmployerId());
                intent.putExtra("EmployerName", myListData.getEmployerName());
                intent.putExtra("EmployerMobileNo", myListData.getEmployerMobileNo());
                intent.putExtra("EmployerEmailId", myListData.getEmployerEmail());
                //intent.putExtra("EmployerAddress", myListData.getEmployerAddress());
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
        public TextView textViewId, textViewName, textViewEmail, textViewMobileNo, textViewAddress;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewEmployerId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewEmployerName);
            this.textViewEmail = (TextView) itemView.findViewById(R.id.textEmployerEmail);
            this.textViewMobileNo = (TextView) itemView.findViewById(R.id.textEmployerMobileNo);
            //this.textViewAddress = (TextView) itemView.findViewById(R.id.textEmployerAddress);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            Context context = itemView.getContext();
        }
    }
}

