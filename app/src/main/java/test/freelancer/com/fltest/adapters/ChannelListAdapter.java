package test.freelancer.com.fltest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.freelancer.com.fltest.R;
import test.freelancer.com.fltest.models.Channel;

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ViewHolder> {

    private List<Channel> channelData;
    private Context context;

    public ChannelListAdapter(Context context, List<Channel> channelData) {
        this.context = context;
        this.channelData = channelData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChannelListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your channelData at this position
        // - replace the contents of the view with that channelData

        Channel fetchedChannel = channelData.get(position);

        if(fetchedChannel != null) {
            viewHolder.tvChannelName.setText(context.getString(R.string.channel_name, fetchedChannel.name));
            viewHolder.tvChannelStartTime.setText(context.getString(R.string.channel_starttime,fetchedChannel.startTime));
            viewHolder.tvChannelEndTime.setText(context.getString(R.string.channel_endtime,fetchedChannel.endTime));
            viewHolder.tvChannelNumber.setText(context.getString(R.string.channel_number,fetchedChannel.number));
            viewHolder.tvChannelRating.setText(context.getString(R.string.channel_rating,fetchedChannel.rating));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvChannelName)
        public TextView tvChannelName;
        @Bind(R.id.tvChannelStartTime)
        public TextView tvChannelStartTime;
        @Bind(R.id.tvChannelEndTime)
        public TextView tvChannelEndTime;
        @Bind(R.id.tvChannelNumber)
        public TextView tvChannelNumber;
        @Bind(R.id.tvChannelRating)
        public TextView tvChannelRating;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }
    }

    @Override
    public int getItemCount() {
        return channelData.size();
    }
}