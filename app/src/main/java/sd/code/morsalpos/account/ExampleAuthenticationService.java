package sd.code.morsalpos.account;

/**
 * Created by master on 11/19/2016.
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ExampleAuthenticationService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return new ExampleAccountAuthenticator(this).getIBinder();
    }
}