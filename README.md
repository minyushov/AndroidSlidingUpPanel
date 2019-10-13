Android Sliding Up Panel
===========================================
[![Download](https://api.bintray.com/packages/minyushov/android/slidinguppanel/images/download.svg)](https://bintray.com/minyushov/android/slidinguppanel/_latestVersion)

This library provides a simple way to add a draggable sliding up panel (popularized by Google Music and Google Maps) to your Android application.


### Integration

```groovy
repositories {
  maven { url "https://dl.bintray.com/minyushov/android" }
}

dependencies {
  implementation "com.minyushov.android:slidinguppanel:x.x.x"
}
```

### Usage

* Include `com.sothree.slidinguppanel.SlidingUpPanelLayout` as the root element in your activity layout.
* The layout must have `gravity` set to either `top` or `bottom`.
* Make sure that it has two children. The first child is your main layout. The second child is your layout for the sliding up panel.
* The main layout should have the width and the height set to `match_parent`.
* The sliding layout should have the width set to `match_parent` and the height set to either `match_parent`, `wrap_content` or the max desireable height. If you would like to define the height as the percetange of the screen, set it to `match_parent` and also define a `layout_weight` attribute for the sliding view.
* By default, the whole panel will act as a drag region and will intercept clicks and drag events. You can restrict the drag area to a specific view by using the `setDragView` method or `umanoDragView` attribute.

For more information, please refer to the sample code.

```xml
<com.sothree.slidinguppanel.SlidingUpPanelLayout
    xmlns:sothree="http://schemas.android.com/apk/res-auto"
    android:id="@+id/sliding_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="bottom"
    sothree:umanoPanelHeight="68dp"
    sothree:umanoShadowHeight="4dp">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="Main Content"
        android:textSize="16sp" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center|top"
        android:text="The Awesome Sliding Up Panel"
        android:textSize="16sp" />
</com.sothree.slidinguppanel.SlidingUpPanelLayout>
```
For smooth interaction with the ActionBar, make sure that `windowActionBarOverlay` is set to `true` in your styles:
```xml
<style name="AppTheme">
    <item name="android:windowActionBarOverlay">true</item>
</style>
```
However, in this case you would likely want to add a top margin to your main layout of `?android:attr/actionBarSize`
or `?attr/actionBarSize` to support older API versions.

### Caveats, Additional Features and Customization

* If you are using a custom `umanoDragView`, the panel will pass through the click events to the main layout. Make your second layout `clickable` to prevent this.
* You can change the panel height by using the `setPanelHeight` method or `umanoPanelHeight` attribute.
* If you would like to hide the shadow above the sliding panel, set `shadowHeight` attribute to 0.
* Use `setEnabled(false)` to completely disable the sliding panel (including touch and programmatic sliding)
* Use `setTouchEnabled(false)` to disables panel's touch responsiveness (drag and click), you can still control the panel programatically
* Use `getPanelState` to get the current panel state
* Use `setPanelState` to set the current panel state
* You can add parallax to the main view by setting `umanoParallaxOffset` attribute (see demo for the example).
* You can set a anchor point in the middle of the screen using `setAnchorPoint` to allow an intermediate expanded state for the panel (similar to Google Maps).
* You can set a `PanelSlideListener` to monitor events about sliding panes.
* You can also make the panel slide from the top by changing the `layout_gravity` attribute of the layout to `top`.
* You can provide a scroll interpolator for the panel movement by setting `umanoScrollInterpolator` attribute. For instance, if you want a bounce or overshoot effect for the panel.
* By default, the panel pushes up the main content. You can make it overlay the main content by using `setOverlayed` method or `umanoOverlay` attribute. This is useful if you would like to make the sliding layout semi-transparent. You can also set `umanoClipPanel` to false to make the panel transparent in non-overlay mode.
* By default, the main content is dimmed as the panel slides up. You can change the dim color by changing `umanoFadeColor`. Set it to `"@android:color/transparent"` to remove dimming completely.

### Scrollable Sliding Views

If you have a scrollable view inside of the sliding panel, make sure to set `umanoScrollableView` attribute on the panel to supported nested scrolling. The panel supports `ListView`, `ScrollView` and `RecyclerView` out of the box, but you can add support for any type of a scrollable view by setting a custom `ScrollableViewHelper`. Here is an example for `NestedScrollView`

```
public class NestedScrollableViewHelper extends ScrollableViewHelper {
  public int getScrollableViewScrollPosition(View scrollableView, boolean isSlidingUp) {
    if (mScrollableView instanceof NestedScrollView) {
      if(isSlidingUp){
        return mScrollableView.getScrollY();
      } else {
        NestedScrollView nsv = ((NestedScrollView) mScrollableView);
        View child = nsv.getChildAt(0);
        return (child.getBottom() - (nsv.getHeight() + nsv.getScrollY()));
      }
    } else {
      return 0;
    }
  }
}
```

Once you define your helper, you can set it using `setScrollableViewHelper` on the sliding panel.

#### Floating Action Button

To include the Floating Action Button to your layout change it to this:
```xml
<com.sothree.slidinguppanel.FloatingActionButtonLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:umanoFabMode=["leave_behind" | "circular_reveal" | "fade"]
    tools:context=".DemoActivity">

    <!-- SLIDING UP PANEL -->
    <com.sothree.slidinguppanel.SlidingUpPanelLayout
        android:id="@+id/sliding_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="bottom"
        app:umanoDragView="@+id/dragView"
        app:umanoPanelHeight="68dp"
        app:umanoParalaxOffset="100dp"
        app:umanoShadowHeight="4dp">
        <!-- The normal content of the Sliding Up Panel -->
    </com.sothree.slidinguppanel.SlidingUpPanelLayout>

    <!-- FLOATING ACTION BUTTON -->
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_marginEnd="16dp"
        android:layout_marginBottom="16dp"
        app:srcCompat="@drawable/icon_add_24"
        app:tint="@android:color/white" />

</com.sothree.slidinguppanel.FloatingActionButtonLayout>
```

You can choose the kind of animation you want for the Floating Action Button when dragging the panel by using the `umanoFabMode` attribute:
* `leave_behind`: This gradually moves the Floating Action Button from the top of the panel header in the collapsed state to the bottom of the header in the expanded state.
* `circular_reveal`: This keeps the Floating Action button on top of the panel header and show or hides the Floating Action Button based on a threshold value of how far the panel has been dragged to the top using a circular reveal animation (see the Google Maps Floating Action Button). Thanks to [@flyingtoaster0](https://github.com/flyingtoaster0) for contributing code!
* `fade`: This animates the alpha value of the Floating Action Button based on how far the panel has been dragged to the top.

There also are some new methods related to the Floating Action Button:
* `setFloatingActionButtonVisibility(int visibility)`: This is a replacment method for the standard `setVisibility()` which doesn't work as intended as this library handles the visibility while sliding the panel. It takes the normal `View.VISIBLE`, `View.INVISIBLE` or `View.GONE` as input. Use this one instead of the default one whenever you want to change the visibility of the Floating Action Button.
* `setFloatingActionButtonAttached(boolean attached)`: This can be used to attach or detach the Floating Action Button from the sliding up panel. When `attached` is `true` the library will move the Floating Action Button along, when it is `false` the Floating Action Button will remain at its' position. **Note:** it is currently your responsibility that the transition from detached to attached mode doesn't result in a position jump.
