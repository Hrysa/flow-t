# flow-t
android toy testing version

## install
1. create project
2. add jitpack repository to your root build.gradle
	
	```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	```

3. add dependence to your app build.gradle
 
	```gradle
	// deps
	implementation 'com.github.Hrysa:flow-t:0.0.1'

	```
	> latest release version see <a href="https://github.com/Hrysa/flow-t/releases">here</a>.
	

## initalization

### create application class (optional)
	
```java
public class DemoApplication extends FlowApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // add State
        // getState().add(new ACacheState(getApplicationContext()));
		// getState().add(new LoginState());
    }
}
```
set class into `AndroidManifest.xml`
	
```xml
	
	<application
		...
   	android:name=".DemoApplication">
```

### extends your theme
in your ``res`->`values`->`styles`.xml
	
```xml
<style name="AppTheme" parent="FlowAppTheme">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
```
> make sure use the correct theme name in `AndroidManifest.xml`

### Create Activity

```java
	public class MainActivity extends FlowActivity {
	    @Override
	    public int getContextViewId() {
	        return R.id.course;
	    }
	
	    @Override
	    protected Fragment onCreateFlow(Bundle bundle) {
	        return new TestFragment();
	    }
	}
``` 
	
### Create Fragment

```java
public class TestFragment extends FlowFragment {
	
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
    	return new FrameLayout(getContext());
    }
}
``` 

### Manage State
> TODO README
	
