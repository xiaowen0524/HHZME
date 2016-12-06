package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hhzmy.bwei.com.hhzme.R;

public class PingjiaActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_pingjia, null);
        return view;
    }
}
