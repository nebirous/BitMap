package com.izv.dam.bitmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ActividadPrincipal extends AppCompatActivity {
    private ImageView iv;
    private EditText etGuardar;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        iv = (ImageView)findViewById(R.id.ivFoto);
        etGuardar = (EditText) findViewById(R.id.editText);
        Intent i = getIntent();
        Uri uri = i.getData();
        iv.setImageURI(uri);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==REQUEST_IMAGE_GET){
            //Bitmap thumbnail = data.getParcelableExtra("data");
            uri = data.getData();
            Log.v("AAAAA", ""+uri);
            if(uri!=null){
                iv.setImageURI ( uri );
            }
        }
    }

    public static final int REQUEST_IMAGE_GET = 1;
    public void btFoto(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
        /*File f = new File (Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS)+"/africa.png");*/
        /*String pathImagen = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS)+"/africa.png";
        BitmapFactory.Options opciones = new BitmapFactory.Options();
        opciones.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathImagen, opciones);
        int anchoFoto = opciones.outWidth;
        int altoFoto = opciones.outHeight;
        int factorEscalado = 2 * ActividadPrincipal.factorDeEscalado(anchoFoto, altoFoto, iv.getWidth(), iv.getHeight());
        factorEscalado = Math.max(anchoFoto/iv.getWidth(),altoFoto/iv.getHeight());
        opciones.inJustDecodeBounds = false;
        opciones.inSampleSize = factorEscalado;
        //opciones.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(pathImagen, opciones);
        iv.setImageBitmap(bitmap);*/
        //Uri uri = Uri.fromFile ( f );
        //iv.setImageURI(uri);
        /*BitmapDrawable bmpDraw = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDraw.getBitmap();
        iv.setImageBitmap(ActividadPrincipal.rotarBitmap(bitmap,45));*/
       // iv.setImageBitmap(ActividadPrincipal.toEscalaDeGris(bitmap));
       /* Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                alpha = Color.alpha(pixel);
                red = 100 + red;
                green = 100 + green;
                blue = 100 + blue;
                alpha = 100 + alpha;
                bmp.setPixel(i, j, Color.argb(alpha, red, green, blue));

                //bitmap.getWidth() - i - 1 dar la vuelta img
            }
        }
        iv.setImageBitmap(bmp);*/

    }
    public static Bitmap toEscalaDeGris(Bitmap bmpOriginal) {
        Bitmap bmpGris = Bitmap.createBitmap(bmpOriginal.getWidth(),
                bmpOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas lienzo = new Canvas(bmpGris);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(10);
        ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(cmcf);
        lienzo.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGris;
    }
    public void rotarDer(View v) {
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bmpOriginal = bmpDOriginal.getBitmap();
        Matrix matriz = new Matrix();
        matriz.postRotate(90);
        Bitmap BitmapDevolver = Bitmap.createBitmap(bmpOriginal, 0, 0, bmpOriginal.getWidth(), bmpOriginal.getHeight(), matriz, true);
        iv.setImageBitmap(BitmapDevolver);
    }
    public void rotarIzq(View v) {
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bmpOriginal = bmpDOriginal.getBitmap();
        Matrix matriz = new Matrix();
        matriz.postRotate(-90);
        Bitmap BitmapDevolver = Bitmap.createBitmap(bmpOriginal, 0, 0, bmpOriginal.getWidth(), bmpOriginal.getHeight(), matriz, true);
        iv.setImageBitmap(BitmapDevolver);
    }
    public void masLuz(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                alpha = Color.alpha(pixel);
                red = 10 + red;
                green = 10 + green;
                blue = 10 + blue;
                alpha = 10 + alpha;
                bmp.setPixel(i, j, Color.argb(alpha, red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void menosLuz(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                alpha = Color.alpha(pixel);
                red = red-10;
                green = green-10;
                blue = blue-10;
                alpha = alpha-10;
                bmp.setPixel(i, j, Color.argb(alpha, red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void masRed(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                red = red+10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));
            }
        }
        iv.setImageBitmap(bmp);
    }
    public void masGreen(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                green = green+10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void masBlue(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                blue = blue+10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void menosRed(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                red = red-10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void menosGreen(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                green = green-10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }
    public void menosBlue(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                blue = blue-10;
                bmp.setPixel(i, j, Color.rgb(red, green, blue));

            }
        }
        iv.setImageBitmap(bmp);
    }

    public void Guardar(View v){
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        String nombre= etGuardar.getText().toString()+".jpg";
        File file = new File(Environment.getExternalStorageDirectory().toString(),nombre);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            tostada(R.string.Guardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int factorDeEscalado (int anchoBmp, int altoBmp,
                                        int anchoIv, int altoIv) {
        int factor = 1;
        if (altoBmp > altoIv || anchoBmp > anchoIv) {
            int alto = altoBmp / 2;
            int ancho = anchoBmp / 2;
            while ((alto / factor) > altoIv && (ancho / factor) > anchoIv) {
                factor = factor * 2;
            }
        }
        return factor;
    }
    public void tostada(int i){
        Toast.makeText(this, i, Toast.LENGTH_LONG).show();
    }
}