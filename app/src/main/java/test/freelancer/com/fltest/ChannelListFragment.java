package test.freelancer.com.fltest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.freelancer.com.fltest.adapters.ChannelListAdapter;
import test.freelancer.com.fltest.interfaces.ChannelInterface;
import test.freelancer.com.fltest.listeners.EndlessScrollListener;
import test.freelancer.com.fltest.models.Channel;
import test.freelancer.com.fltest.models.ChannelResults;

/**
 * List that displays the TV Programmes
 */
public class ChannelListFragment extends Fragment {

    public final String BASE_URL = "http://whatsbeef.net/";
    public final int ROWS_PER_PAGE = 10;

    private LinearLayoutManager mLayoutManager;
    private ChannelListAdapter mAdapter;

    private List<Channel> channelDataList;
    private int totalChannelsCount;

    private boolean loading = true;
    private int currentPageIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, recyclerView);

        channelDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getActivity());

        currentPageIndex = 0;
        totalChannelsCount = 0;

        //TEST DATA
        /*
        channelDataList.add(new Channel("Criminal Minds", "8:45PM", "10:40PM", "Seven", "M"));
        channelDataList.add(new Channel("Hannibal", "10:40PM", "11:35PM", "Seven", "AV"));
        */

        mAdapter = new ChannelListAdapter(getActivity(), channelDataList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onScrolledToEnd() {
                if (!loading) {
                    loading = true;
                    // add 10 by 10 to tempList then notify changing in data
                    if(totalChannelsCount > ((currentPageIndex+1) * ROWS_PER_PAGE)) {
                        currentPageIndex++;
                        Log.d("TEST", "Getting next page: " + currentPageIndex);
                        connect("http://whatsbeef.net/", currentPageIndex);
                    } else {
                        Log.d("TEST", "Next Page Not Available");
                    }
                } else {
                    Log.d("TEST", "Still Loading!");
                }
                loading = false;
            }
        });

        return recyclerView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(getActivity());
        connect(BASE_URL,currentPageIndex);
    }

    private void refreshList(List<Channel> updatedChannelList) {
        Log.d("Refresh", "Refreshing List...");
        channelDataList.addAll(updatedChannelList);
        mAdapter.notifyDataSetChanged();
    }

    private void restoreSavedChannelsList() {
        List<Channel> cachedChannels = Select.from(Channel.class).list();
        if(cachedChannels != null && !cachedChannels.isEmpty()) {
            refreshList(cachedChannels);
        }
    }

    public void connect(String baseUrl, int pageNumber) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChannelInterface channelInterface = retrofit.create(ChannelInterface.class);
        Call<ChannelResults> channelResults = channelInterface.channelResult(pageNumber*ROWS_PER_PAGE);

        Toast.makeText(getActivity(), getString(R.string.loading_please_wait), Toast.LENGTH_LONG).show();
        channelResults.enqueue(new Callback<ChannelResults>() {
            @Override
            public void onResponse(Call<ChannelResults> call, Response<ChannelResults> response) {
                Log.d("Resp", "The Response for page: " + " " + currentPageIndex + " .... "+response.body());

                ChannelResults channelResult = response.body();
                if(channelResult != null) {
                    if(channelResult.results != null) {
                        List<Channel> channelList = channelResult.results;
                        for(Channel channel : channelList) {
                            //channel.save();
                            Log.i("RECEIVED", "Channel: " + channel.name);
                        }
                        refreshList(channelList);
                    } else {
                        Log.e("ERR", "ChannelResult.results NULL");
                    }

                    totalChannelsCount = channelResult.count;
                } else {
                    Log.e("ERR", "ChannelResult NULL");
                }
            }

            @Override
            public void onFailure(Call<ChannelResults> call, Throwable t) {
                Log.d("Resp", "Failure.. Reason:" + t.getMessage());
                restoreSavedChannelsList();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        /*SugarRecord.deleteAll(Channel.class);
        for(Channel channel : channelDataList) {
            channel.save();
        }*/
    }
}
