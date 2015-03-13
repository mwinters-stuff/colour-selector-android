# Introduction #

Once you have the source, add the library to eclipse in the usual manner, and then add the library to your android project.


# Preference #

Using as a preference is quite simple, the preference value is an integer. Insert the following into your preference xml file in the required place..

```xml

<nz.org.winters.android.common.colourselector.ColourPreference
android:key="colourBatteryNotCharging"
android:title="@string/not_charging" />
```

# Dialog #

Opening up the colour selector as a dialog activity is easy too, for example from some menu item or button add the following code..

```
        ColorSelectorDialog dialog = new ColorSelectorDialog(mContext, "title", new OnColorChangedListener()
        {
          public void colorChanged(int color)
          {
             // colour selected is in color..
          }
        }, initialcolour);
        dialog.show();

```