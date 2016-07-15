package com.example.wanglz.pokemon;

import android.location.Location;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.Buffer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by wanglz on 16/7/15.
 */
public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.nianticlabs.pokemongo"))

            return  ;
        XposedBridge.log("Loaded app:" + loadPackageParam.packageName);

        findAndHookMethod("android.location.LocationManager", loadPackageParam.classLoader, "getLastKnownLocation",
                String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("location now is:" + param.args[0].toString());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        Location location = (Location) param.getResult();
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            XposedBridge.log("location:" + latitude + "," + longitude);

                        } else {
                            XposedBridge.log("location is null");

                        }

                    }
                });
        //改变精度
        findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "getLatitude",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        double result = Double.parseDouble(param.getResult().toString());

                        //double modify =  FileIO.read("/sdcard/pokemon/lat"));
                        File file  = new File("/sdcard/pokemon/lat.txt");
                        String s ="";
                        BufferedReader br =new BufferedReader(new FileReader(file));
                        s = br.readLine();

                        double modify = Double.parseDouble(s);
                        param.setResult(modify);
                        XposedBridge.log("Location latitude changed : "+ modify);




                    }
                });
        //改变纬度
        findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "getLongitude",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        double result = Double.parseDouble(param.getResult().toString());

                        //double modify =  FileIO.read("/sdcard/pokemon/lat"));
                        File file  = new File("/sdcard/pokemon/lon.txt");
                        String s ="";
                        BufferedReader br =new BufferedReader(new FileReader(file));
                        s = br.readLine();

                        double modify = Double.parseDouble(s);
                        param.setResult(modify);
                        XposedBridge.log("Location Longitude changed : "+ modify);


                    }
                });

    }

}
