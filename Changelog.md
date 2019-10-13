Change Log
==========

## Version 3.5.0

 *  Migrate to AndroidX.
 *  Update dependencies.


## Version 3.4.2

 *  Fix layout logic when panel is anchored.
 *  Update dependencies.


## Version 3.4.1

 *  Merge latest changes from the original library.
 *  Update dependencies.


## Version 3.4.0

 *  Use the latest support library 26 and update the min version to 14.
 *  Bug fixes


## Version 3.3.1

 *  Lots of bug fixes from various pull requests.
 *  Removed the nineoldandroids dependency. Use ViewCompat instead.


## Version 3.3.0

 *  You can now set a `FadeOnClickListener`, for when the faded area of the main content is clicked.
 *  `PanelSlideListener` has a new format (multiple of them can be set now
 *  Fixed the setTouchEnabled bug


## Version 3.2.1

 *  Add support for `umanoScrollInterpolator`
 *  Add support for percentage-based sliding panel height using `layout_weight` attribute
 *  Add `ScrollableViewHelper` to allow users extend support for new types of scrollable views.


## Version 3.2.0

 *  Rename `umanoParalaxOffset` to `umanoParallaxOffset`
 *  RecyclerView support.


## Version 3.1.0

 *  Added `umanoScrollableView` to supported nested scrolling in children (only ScrollView and ListView are supported for now)


## Version 3.0.0

 *  Added `umano` prefix for all attributes
 *  Added `clipPanel` attribute for supporting transparent panels in non-overlay mode.
 *  `setEnabled(false)` - now completely disables the sliding panel (touch and programmatic sliding)
 *  `setTouchEnabled(false)` - disables panel's touch responsiveness (drag and click), you can still control the panel programatically
 *  `getPanelState` - is now the only method to get the current panel state
 *  `setPanelState` - is now the only method to modify the panel state from code


## Version 2.0.2

 *  Allow `wrap_content` for sliding view height attribute. Bug fixes. 


## Version 2.0.1

 *  Bug fixes. 


## Version 2.0.0

 *  Cleaned up various public method calls. Added animated `showPanel`/`hidePanel` methods. 


## Version 1.0.1 

 *  Initial Release 
