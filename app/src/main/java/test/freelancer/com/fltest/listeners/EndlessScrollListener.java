package test.freelancer.com.fltest.listeners;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by user on 4/21/2016.
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = EndlessScrollListener.class.getSimpleName();

    // use your LayoutManager instead
    private LinearLayoutManager llm;

    public EndlessScrollListener(LinearLayoutManager sglm) {
        this.llm = llm;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (!recyclerView.canScrollVertically(1)) {
                onScrolledToEnd();
            }
        }
    }

    public abstract void onScrolledToEnd();
}