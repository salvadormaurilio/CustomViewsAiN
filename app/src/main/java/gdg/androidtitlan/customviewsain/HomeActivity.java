package gdg.androidtitlan.customviewsain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.androidtitlan.customviewsain.customview.CustomViewCanvas;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.customViewCanvas)
    CustomViewCanvas viewCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.floatingButtonEraser)
    public  void onClickEraser ()
    {
        viewCanvas.clearCanvas();
    }

}
