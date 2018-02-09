package com.bingo.finger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public static final String KEY_PROVIDER = "AndroidKeyStore";
    public static final String KEY_NAME = "DefaultName";

    Button btn;
    ImageView fingerprint_icon;

    KeyStore keyStore;
    Cipher cipher;
    FingerprintManager fingerprintManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.email_sign_in_button);
        fingerprint_icon = findViewById(R.id.fingerprint_icon);

        initFingerprint();

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && initCipher(cipher, KEY_NAME)) {
                    btn.setText("开始验证");
                    fingerprint_icon.setImageResource(R.mipmap.ic_fp_40px);
                    fingerprintManager.authenticate(new FingerprintManager.CryptoObject(cipher),
                            new CancellationSignal(), 0,
                            new FingerprintManager.AuthenticationCallback() {
                                @Override
                                public void onAuthenticationError(int errorCode, CharSequence errString) {
                                    super.onAuthenticationError(errorCode, errString);
                                    btn.setText("验证失败");
                                    fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_error);
                                }

                                @Override
                                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                                    super.onAuthenticationHelp(helpCode, helpString);
                                }

                                @Override
                                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                                    super.onAuthenticationSucceeded(result);
                                    fingerprint_icon.setImageResource(R.drawable.ic_fingerprint_success);
                                    btn.setText("验证成功");
                                }

                                @Override
                                public void onAuthenticationFailed() {
                                    super.onAuthenticationFailed();
                                }
                            }, null);
                }
            }
        });
    }


    public void initFingerprint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // step 1 新建keystore
            try {
                keyStore = KeyStore.getInstance(KEY_PROVIDER);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            // step 2 获取KeyGenerator密钥生成工具，生成密钥
            KeyGenerator keyGen;
            try {
                keyGen = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_PROVIDER);
                KeyGenParameterSpec.Builder keyGenBuilder = new KeyGenParameterSpec.Builder(KEY_NAME,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    keyGenBuilder.setInvalidatedByBiometricEnrollment(true);
                }
                keyGen.init(keyGenBuilder.build());
                keyGen.generateKey();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }


            // step 3 通过密钥初始化Cipher对象
            try {
                cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }

            fingerprintManager = getSystemService(FingerprintManager.class);
            if (fingerprintManager != null
                    && fingerprintManager.isHardwareDetected()
                    && fingerprintManager.hasEnrolledFingerprints()) {
                btn.setEnabled(true);
            } else {
                btn.setText("指纹验证不可用");
                btn.setEnabled(false);
            }

        } else {

        }
    }

    public boolean initCipher(Cipher cipher, String keyName) {
        try {
            if (keyStore != null) {
                keyStore.load(null);
                SecretKey key = (SecretKey) keyStore.getKey(keyName, null);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return false;
    }

}

