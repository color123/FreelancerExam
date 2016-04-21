package test.freelancer.com.fltest;

import com.orm.SugarContext;

/**
 * Created by user on 4/21/2016.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
