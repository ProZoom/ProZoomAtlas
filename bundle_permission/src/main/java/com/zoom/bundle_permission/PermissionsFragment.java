package com.zoom.bundle_permission;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class PermissionsFragment extends Fragment implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_permission, null);
        initView(root);
        return root;
    }

    private void initView(View root) {

        Button writeExternalStorage = (Button) root.findViewById(R.id.write_external_storage);
        Button readExternalStorage = (Button) root.findViewById(R.id.read_external_storage);
        Button accessCoarseLocation = (Button) root.findViewById(R.id.access_coarse_location);
        Button accessFineLocation = (Button) root.findViewById(R.id.access_fine_location);
        Button readPhoneState = (Button) root.findViewById(R.id.read_phone_state);
        Button callPhone = (Button) root.findViewById(R.id.call_phone);
        Button getAccounts = (Button) root.findViewById(R.id.get_accounts);
        Button recordAudio = (Button) root.findViewById(R.id.record_audio);
        Button showCamera = (Button) root.findViewById(R.id.show_camera);

        writeExternalStorage.setOnClickListener(this);
        readExternalStorage.setOnClickListener(this);
        accessCoarseLocation.setOnClickListener(this);
        accessFineLocation.setOnClickListener(this);
        readPhoneState.setOnClickListener(this);
        callPhone.setOnClickListener(this);
        getAccounts.setOnClickListener(this);
        recordAudio.setOnClickListener(this);
        showCamera.setOnClickListener(this);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.write_external_storage) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, mPermissionGrant);

        } else if (i == R.id.read_external_storage) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);

        } else if (i == R.id.access_coarse_location) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_COARSE_LOCATION, mPermissionGrant);

        } else if (i == R.id.access_fine_location) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_FINE_LOCATION, mPermissionGrant);

        } else if (i == R.id.read_phone_state) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_READ_PHONE_STATE, mPermissionGrant);

        } else if (i == R.id.call_phone) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_CALL_PHONE, mPermissionGrant);

        } else if (i == R.id.get_accounts) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_GET_ACCOUNTS, mPermissionGrant);

        } else if (i == R.id.record_audio) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_RECORD_AUDIO, mPermissionGrant);

        } else if (i == R.id.show_camera) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_CAMERA, mPermissionGrant);

        } else {
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        PermissionUtils.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, mPermissionGrant);

    }
}
