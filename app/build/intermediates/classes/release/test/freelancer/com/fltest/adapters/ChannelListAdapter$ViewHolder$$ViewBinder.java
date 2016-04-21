// Generated code from Butter Knife. Do not modify!
package test.freelancer.com.fltest.adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChannelListAdapter$ViewHolder$$ViewBinder<T extends test.freelancer.com.fltest.adapters.ChannelListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296339, "field 'tvChannelName'");
    target.tvChannelName = finder.castView(view, 2131296339, "field 'tvChannelName'");
    view = finder.findRequiredView(source, 2131296340, "field 'tvChannelStartTime'");
    target.tvChannelStartTime = finder.castView(view, 2131296340, "field 'tvChannelStartTime'");
    view = finder.findRequiredView(source, 2131296341, "field 'tvChannelEndTime'");
    target.tvChannelEndTime = finder.castView(view, 2131296341, "field 'tvChannelEndTime'");
    view = finder.findRequiredView(source, 2131296342, "field 'tvChannelNumber'");
    target.tvChannelNumber = finder.castView(view, 2131296342, "field 'tvChannelNumber'");
    view = finder.findRequiredView(source, 2131296343, "field 'tvChannelRating'");
    target.tvChannelRating = finder.castView(view, 2131296343, "field 'tvChannelRating'");
  }

  @Override public void unbind(T target) {
    target.tvChannelName = null;
    target.tvChannelStartTime = null;
    target.tvChannelEndTime = null;
    target.tvChannelNumber = null;
    target.tvChannelRating = null;
  }
}
