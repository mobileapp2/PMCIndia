package com.imuons.pmcindia.fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.GetPackageHouseModel;
import com.imuons.pmcindia.ResponseModel.GetPackageRecordModel;
import com.imuons.pmcindia.ResponseModel.GetPackageResponseModel;
import com.imuons.pmcindia.adapters.InvestmentGridAdapter;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.MultipartRequest;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.Constants;
import com.imuons.pmcindia.view.DashboardActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInvestment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInvestment extends Fragment implements InvestmentGridAdapter.ClickEvent {
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int GALLERY_PICTURE_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int INITIAL_REQUEST_CAMERA = 1338;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private static final String[] CAMERA_PERM = {Manifest.permission.CAMERA};
    private static final String IMAGE_DIRECTORY_NAME = "PMC";
    private static final int PICK_FILE_REQUEST = 1;
    private static OnResponseHandle onResponseHandle;
    private final String twoHyphens = "------";
    private final String lineEnd = "\r\n";
    private final String boundary = "WebKitFormBoundary" + System.currentTimeMillis();
    private final String mimeType = "multipart/form-data;boundary=" + boundary;
    public boolean is_payment_dialog_open;
    public RelativeLayout dialogBox;
    EditText etAmount;
    TextView tv_makePayment;
    TextView amt;
    TextView remainingamt;
    TextView tvPaidAmount;
    TextView tvCurencyType;
    TextView tvPaidCurrencyType;
    ImageView qrcode;
    TextView link;
    TextView message;
    TextView comingvalue;
    TextView price;
    TextView mTextField;
    Button copyto;
    Button closeBtn;
    String currency_code;
    EditText et_tran_hash;
    TextView tv_choose_file;
    TextView tv_file_name;
    Button btn_submit;
    String newlink, strinvoiceid, straddress;
    int finalvalue;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private GridView gridview;
    private int REQUEST_WRITE_PERMISSION_FILE_CHOSER = 1000;
    private String selectedFilePath;
    private Uri fileUri;
    private String filePath = "";
    private String cameraFlag;
    private String tag = "FragmentInvestment";
    private File destination;
    private Uri selected_file_uri;
    private Bitmap bitmapImge;
    private InvestmentGridAdapter investmentGridAdapter;
    private InvestmentGridAdapter.ViewHolder viewholder;
    private LinearLayout ll_pending_amount;
    private LinearLayout ll_paid_amounnt;
    private Button copyto_invoice;
    private LinearLayout ll_invoice_id_layer;
    private TextView tv_invoice_id;
    private boolean is_popup_image;
    private String amount;

    public FragmentInvestment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentInvestment newInstance(DashboardActivity dashboardActivity) {
        FragmentInvestment fragment = new FragmentInvestment();
        onResponseHandle = dashboardActivity;
        return fragment;
    }

    private static File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        // BEST QUALITY MATCH First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        // Raw height and width of image
        final int height = 600;
        final int width = 800;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }

        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }


        Bitmap bm;
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_investment, container, false);
        intUI();
        return view;
    }

    private void intUI() {
        gridview = view.findViewById(R.id.gridview);

        ll_pending_amount = view.findViewById(R.id.ll_pending_amount);
        ll_paid_amounnt = view.findViewById(R.id.ll_paid_amount);

        tv_invoice_id = view.findViewById(R.id.tv_invoice_id);
        copyto_invoice = view.findViewById(R.id.copyto_invoice);
        ll_invoice_id_layer = view.findViewById(R.id.ll_invoice_copy);

        amt = view.findViewById(R.id.amt);
        remainingamt = view.findViewById(R.id.remainingamt);
        tvPaidAmount = view.findViewById(R.id.tvPaidAmount);
        qrcode = view.findViewById(R.id.qrcode);
        link = view.findViewById(R.id.link);
        message = view.findViewById(R.id.message);
        comingvalue = view.findViewById(R.id.comingvalue);

        copyto = view.findViewById(R.id.copyto);
        mTextField = view.findViewById(R.id.mTextField);
        closeBtn = view.findViewById(R.id.closeBtn);
        dialogBox = view.findViewById(R.id.dialogBox);
        tvCurencyType = view.findViewById(R.id.tvCurencyType);
        tvPaidCurrencyType = view.findViewById(R.id.tvPaidCurrencyType);
        price = view.findViewById(R.id.price);

        et_tran_hash = view.findViewById(R.id.et_tran_hash);
        tv_choose_file = view.findViewById(R.id.tv_choose_file);
        tv_file_name = view.findViewById(R.id.file_name);
        btn_submit = view.findViewById(R.id.submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_tran_hash.getText().toString().trim().isEmpty()) {
                    et_tran_hash.setError("Enter Transaction Hash");
                    et_tran_hash.requestFocus();
                    return;
                } else if (filePath.equals("")) {
                    Toast.makeText(getContext(), "Upload Payment slip", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    setFundRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        callRequestreport();

        copyto_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strinvoiceid != null) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", strinvoiceid);
                    clipboard.setPrimaryClip(clip);
                    copyto_invoice.setText("Copied");
                    copyto.setText("Copy");
                } else {
                    Toast.makeText(getContext(), "Invoice Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copyto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (straddress != null) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", straddress);
                    clipboard.setPrimaryClip(clip);
                    copyto.setText("Copied");
                    copyto_invoice.setText("Copy");
                } else {
                    Toast.makeText(getContext(), "Referal Link Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.setVisibility(View.GONE);
                is_payment_dialog_open = false;
            }
        });
        tv_choose_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePath = "";
                is_popup_image = true;
                showPictureDialog();
            }
        });
    }


    private void callRequestreport() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            Call call = apiService.GetPackages();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    // progressBar.setVisibility(View.GONE);
                    GetPackageResponseModel packageResponseModel = (GetPackageResponseModel) response.body();
                    if (packageResponseModel != null) {
                        Log.i("requets fund::", new Gson().toJson(packageResponseModel));
                        if (packageResponseModel.getCode() == 200) {
                            setadpter(packageResponseModel.getData());
                        } else {
                            Toast.makeText(getContext(), packageResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getContext()).showDialog(getActivity(), getResources().getString(R.string.server_error));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    // progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    // loaderView.setVisibility(View.GONE);
                    //  Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // no internet
            //  progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setadpter(List<GetPackageRecordModel> data) {
        investmentGridAdapter = new InvestmentGridAdapter(getActivity(), data, FragmentInvestment.this);
        gridview.setAdapter(investmentGridAdapter);
    }

    @Override
    public void clickFileChooser(InvestmentGridAdapter.ViewHolder viewHolder) {
        filePath = "";
        this.viewholder = viewHolder;
        is_popup_image = false;
        showPictureDialog();
    }

    @Override
    public void clickMakeyPayment(GetPackageRecordModel packageRecordModel, String amount,
                                  String type, GetPackageHouseModel selectedhouse) {
        if (type.equals("INR")) {
            if (filePath.equals("")) {
                Toast.makeText(getContext(), "Upload Payment slip", Toast.LENGTH_SHORT).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    try {
                        this.amount = amount;
                        if(selectedhouse!=null){
                            if( packageRecordModel.getHouses().size()>0){
                                if(selectedhouse.getId()!=null){
                                    Integer id=selectedhouse.getId();
                                            callService(topupRequest(packageRecordModel, amount, type,id));
                                }else{
                                    Toast.makeText(getContext(), "Select House", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            callService(topupRequest(packageRecordModel, amount, type,null));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    if(selectedhouse!=null){
                        if( packageRecordModel.getHouses().size()>0){
                            if(selectedhouse.getId()!=null){
                                Integer id=selectedhouse.getId();
                                callService(topupRequest(packageRecordModel, amount, type,id));
                            }else{
                                Toast.makeText(getContext(), "Select House", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        callService(topupRequest(packageRecordModel, amount, type,null));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {getResources().getString(R.string.Select_photo_from_gallery), getResources().getString(R.string.Capture_photo_from_camera)};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        choosePhotoFromGallary();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                requestpermission(1);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                try {
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    getActivity().startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                    //                    Toast.makeText(getContext(),"Something went Wrong", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //            Toast.makeText(getContext(),"Storage area Something went Wrong "+e.getMessage(),
            //                    Toast.LENGTH_LONG).show();
        }
    }

    private void takePhotoFromCamera() {
        try {
            cameraFlag = "click";
            if (Build.VERSION.SDK_INT >= 23) {
                requestpermission(2);


            } else {

                try {
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    getActivity().startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


    private void requestpermission(int i) {
        if (i == 1) {
            int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                Log.d(tag, "----req permission-GALLERY_PICTURE_REQUEST_CODE--" + fileUri);
                galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                getActivity().startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);

            } else {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
            }

        } else if (i == 2) {
            int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {

                if (cameraFlag != null) {
                    if (cameraFlag.equals("click")) {
                        Log.d(tag, "-----------------camera---------------" + cameraFlag);
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        getActivity().startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

                    }
                    cameraFlag = null;
                } else {

                }
            } else {
                requestPermissions(CAMERA_PERM, INITIAL_REQUEST_CAMERA);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_PERMISSION_FILE_CHOSER) {
            int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "No File  Choose option", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_WRITE_PERMISSION) {
            int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                Log.d(tag, "----req permission-GALLERY_PICTURE_REQUEST_CODE--" + fileUri);
                galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                getActivity().startActivityForResult(galleryIntent, GALLERY_PICTURE_REQUEST_CODE);
                //Toast.makeText(getContext(),"Gallery Permission Allow", Toast.LENGTH_LONG).show();
            } else {
                // Toast.makeText(getContext(),"Gallery Permission Denied", Toast.LENGTH_LONG)
                // .show();
            }
        } else if (requestCode == INITIAL_REQUEST_CAMERA) {
            int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {

                if (cameraFlag != null) {
                    if (cameraFlag.equals("click")) {
                        Log.d(tag, "-----------------camera---------------" + cameraFlag);
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        getActivity().startActivityForResult(intent1, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

                    }
                    cameraFlag = null;
                } else {

                }
            }
        }

    }

    public void setImage(int requestCode, int resultCode, Intent data) {
        Log.d(tag, "---resulte coddde-request code-" + resultCode + "--" + requestCode);
        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (data == null) {
                    //no data present
                    return;
                }
                if (resultCode == RESULT_OK) {

                    try {

                        onCaptureImageResult(data);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    //   Toast.makeText(getApplicationContext(), getString(R.string.canclecaptcheimage), Toast.LENGTH_SHORT).show();
                } else {
                    // user cancelled Image capture

                }
            } else if (requestCode == GALLERY_PICTURE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    // video successfully recorded
                    // preview the recorded video
                    selected_file_uri = data.getData();
                    filePath = getPath(data.getData());
                    // Bitmap bm = BitmapFactory.decodeFile(filePath);
                    if (is_popup_image) {
                        tv_file_name.setText(new File(filePath).getName());
                    } else {
                        investmentGridAdapter.setfileName(viewholder, new File(filePath).getName());
                    }

                    bitmapImge = decodeSampledBitmapFromFile(filePath, 600, 800);

                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled recording


                } else {

                }
            }
        }
    }

    private String getPath(Uri uri) {

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = 0;
        try {
            // cursor = CaptureVehicleDetailActivity.this.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                return cursor.getString(column_index);
            }
        } finally {
            //if (cursor != null)
            //  cursor.close();
        }
        assert cursor != null;
        return cursor.getString(column_index);
    }

    private void onCaptureImageResult(Intent data) {
        bitmapImge = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapImge.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        if (is_popup_image) {
            tv_file_name.setText(destination.getName());
        } else {
            investmentGridAdapter.setfileName(viewholder, destination.getName());
        }

        Log.d("destination", "" + destination.getName());
        // filePath = Environment.getExternalStorageDirectory().toString() + System.currentTimeMillis() + ".jpg";
        filePath = destination.getAbsolutePath();
        Log.d("filePath", "" + filePath);
        if (destination != null) {

        } else {
            Log.e("erroe", " onCaptureImageResult send null file");
        }

    }


    private byte[] topupRequest(GetPackageRecordModel packageRecordModel, String amount,
                                String type, Integer id) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            Map<String, RequestBody> param1 = new HashMap<>();
            if (!filePath.equals("")) {
                byte[] fileData1 = getFileDataFromDrawable(getContext(), filePath);
                File uu = new File(filePath);
                buildPart(dos, fileData1, uu.getName() + ".png");
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                buildTextPart(dos, "product_id", String.valueOf(packageRecordModel.getId()));
                buildTextPart(dos, "currency_code", type);
                buildTextPart(dos, "hash_unit", amount);
                if(id!=null){
                    buildTextPart(dos, "house_id",String.valueOf(id));
                }

            }

            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


        } else {
            // no internet
            //  progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

        return bos.toByteArray();
    }

    private void callService(byte[] multipartBody) {
        String url = ServiceGenerator.API_BASE_URL + "getaddress";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Authorization", "Bearer " + AppCommon.getInstance(getContext()).getToken());
        params.put("Content-Type", "multipart/form-data; boundary=----" + boundary);
        Log.d(tag, "--auth--" + params.toString());
        MultipartRequest multipartRequest = new MultipartRequest(url, params, mimeType, multipartBody, response -> {
            String resultResponse = new String(response.data);
            try {
                Log.d("response--", "-------------" + new JSONObject(resultResponse));
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                if (getActivity().isFinishing())
                    return;

                JSONObject jsonObject = new JSONObject(resultResponse);
                handleResponse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
            }

        });

        Volley.newRequestQueue(getContext()).add(multipartRequest);
    }

    private void handleResponse(JSONObject jsonObject) throws JSONException {
        if (jsonObject.getString("code").equals("409")) {
            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        } else if (jsonObject.getString("code").equals("200")) {
            if (jsonObject.has("data")) {
                JSONObject dataobj = jsonObject.getJSONObject("data");
                if (dataobj.has("network_type")) {
                    if (dataobj.getString("network_type").equals("BTC")) {
                        is_payment_dialog_open = true;
                        dialogBox.setVisibility(View.VISIBLE);
                        filePath = "";
                        setDepositDialog(dataobj);
                    }
                } else {
                    filePath = "";
                    onResponseHandle.onResponse(1);
                    Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            //  {"code":404,"status":"Not Found","message":"You can make only one investment!",
            //  "data":{}}
            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        }
    }

    private void setDepositDialog(JSONObject dataobj) throws JSONException {
        comingvalue.setText("Package "+Constants.currency_Ruppes_symbol + " " + dataobj.getInt(
                "price_in_usd"));
        amt.setText(Constants.currency_Ruppes_symbol + " " + dataobj.getInt("price_in_usd"));
      //  amt.setText(Constants.currency_Ruppes_symbol + amount);
//        price.setText("( " + String.format("%.4f", dataobj.getDouble("price_in_usd")) + " BTC )");
//        tvPaidAmount.setText(String.valueOf(dataobj.getDouble("received_amount")));
//        tvCurencyType.setText("BTC");
//        tvPaidCurrencyType.setText("BTC");
        BigDecimal bd = new BigDecimal(dataobj.getDouble("price_in_usd"));
        BigDecimal bd2 = new BigDecimal(dataobj.getDouble("received_amount"));

        BigDecimal remainingint = bd.subtract(bd2).setScale(8, BigDecimal.ROUND_HALF_UP);
        straddress = dataobj.getString("address");
        newlink = "bitcoin:" + dataobj.getString("address") + "?amount=" + String.format("%.6f", dataobj.getDouble("price_in_usd"));
        setQr(newlink);
        BigDecimal value0 = new BigDecimal(0);
        finalvalue = remainingint.compareTo(value0);
        if (finalvalue <= 0) {
            remainingamt.setText("0");

        } else {
            remainingamt.setText(remainingint.toPlainString());
        }
        link.setText(dataobj.getString("address"));
        tv_invoice_id.setText(dataobj.getString("invoice_id"));
//        message.setText("After Making Payment, Relax and enjoy your coffee with smile on your face because our auto system will confirm your transaction after getting 3 confirmation on Explorer");
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);

    }

    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
        Log.d("build part 1", "--------------");
        if (dataOutputStream == null) {
            Log.e("Registration", "dataOutputStream is null");
        }
        if (fileData == null) {
            Log.e("Registration", "fileData is null");
        }
        if (fileName == null) {
            Log.e("Registration", "fileName is null");
        }
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        Log.d("build part 2", "--------------");
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();
        Log.d("build part 3", "--------------");
        int maxBufferSize = 1024 * 10;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        Log.d("build part 4", "--------------");
        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }
        Log.d("build part 5", "--------------");

        dataOutputStream.writeBytes(lineEnd);
    }

    private byte[] getFileDataFromDrawable(Context context, String filePath) {

        Log.d("bitmap image", "--------------" + filePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Log.d("bitmap 1", "--------------");

        Log.d("bitmap 2", "--------------");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (byteArrayOutputStream == null) {
            Log.e("byte null", "byteArrayOutputStream nill ************************");
        }

        bitmapImge.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);

        byte[] b = byteArrayOutputStream.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("encode image ", "--------------" + imageEncoded);
        return byteArrayOutputStream.toByteArray();
    }

    private void setQr(String newlink) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(newlink, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            qrcode.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void setFundRequest() throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        if (!filePath.equals("")) {
            byte[] fileData1 = getFileDataFromDrawable(getContext(), filePath);
            File uu = new File(filePath);
            buildPart(dos, fileData1, uu.getName() + ".png");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            buildTextPart(dos, "transaction_hash", et_tran_hash.getText().toString());
            buildTextPart(dos, "invoice_id", tv_invoice_id.getText().toString());
        }

        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
        fundRequest(bos.toByteArray());
    }

    private void fundRequest(byte[] multipartBody) {
        String url = ServiceGenerator.API_BASE_URL + "fund-request";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Authorization", "Bearer " + AppCommon.getInstance(getContext()).getToken());
        params.put("Content-Type", "multipart/form-data; boundary=----" + boundary);
        Log.d(tag, "--auth--" + params.toString());
        MultipartRequest multipartRequest = new MultipartRequest(url, params, mimeType, multipartBody, response -> {
            String resultResponse = new String(response.data);
            try {
                Log.d("fund response--", "-------------" + new JSONObject(resultResponse));
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                if (getActivity().isFinishing())
                    return;
                parsefundRequest(new JSONObject(resultResponse));

            } catch (JSONException e) {
                e.printStackTrace();
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
            }

        });

        Volley.newRequestQueue(getContext()).add(multipartRequest);
    }

    private void parsefundRequest(JSONObject jsonObject) throws JSONException {
        if (jsonObject.getString("code").equals("409")) {
            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        } else if (jsonObject.getString("code").equals("200")) {
            if (jsonObject.has("data")) {
                JSONObject dataobj = jsonObject.getJSONObject("data");
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                is_payment_dialog_open = true;
                dialogBox.setVisibility(View.GONE);
                filePath = "";
                onResponseHandle.onResponse(1);
            }
        } else {

            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        }
    }

    public interface OnResponseHandle {
        void onResponse(int flag);
    }

}
