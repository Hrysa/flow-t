### 0.0.1
  ...
  
### 0.0.2
  add nav bar button change visibility method.

### 0.0.3

#### remove
* FlowFragment
	* hideNav()
	* showNav()
* NavBarVModel
	* hideLeftIcon()
	* showLeftIcon()
	* hideRightIcon()
	* showRightIcon()

#### add

* NavBarModel
	* setNavBarVisibility(int type)
	* setRightIconVisibility(int type)
	* setLeftIconVisibility(int type)


#### add annotation @FlowState

```
public class HomeFragment extends FlowFragment {
	// before
	LoginState loginState = getState().get(LoginState.class);

	// now
	@FlowState
    LoginState loginState;
```
> this annotation allow you to quickly bind the state into fragment.

