/*
package com.mahendra.shopperstock.fragment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import peelyourdeal.best.price.compare.app.googleanalytics.MyApplication;
import peelyourdeal.best.price.compare.app.network.APIResponse;
import peelyourdeal.best.price.compare.app.network.PYDResponseController;
import peelyourdeal.best.price.compare.app.util.CommonMethods;
import peelyourdeal.best.price.compare.app.util.Constant;
import peelyourdeal.best.price.compare.appVO.GSONParseProduct.GsonParseProduct;
import peelyourdeal.best.price.compare.appVO.ProductDetailByName;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainViewFragment extends Fragment implements
		PYDResponseController, OnItemSelect {

	private ListView productlistView;
	private GridView productgridview;

	private ListView productFullList;
	public SharedPreferences MyPrefs;



	public TypedArray all_icon;

	private String temp_view = "";
	private String temp_click = "";
	private String str_direct, str_product_name_code;

	private Constant _constant; // = new Constant((MainActivity)getActivity());
	private CommonMethods _common;// = new
									// CommonMethods((MainActivity)getActivity(),
									// this);
	private int pageNumberForService = 1;
	private int statusprogressbarshow_footer = 1;

	private GsonParseProduct gsonProductDetail;
	private int int_total_page;
	private boolean loadingMore = false;
	private Activity _activity;
	public boolean CallGsonReq = true;
	public int status;
	private String order = "DESC";
	private String tempo;
	private MyCustomAdap myCustomAdap;
	private MygridAdapter_Adapt gridAdapter;
	private MyFullAdapter_Adapt fullAdapter;
	public int height;
	public int width;
	public String strName_ = "";

	private String blockCharacterSet = "~#^|$%&*!\"";
	// private View foot;

	private boolean st = true;

	@SuppressWarnings("unused")
	private TextView footermsg;
	// private View foot;
	@SuppressWarnings("unused")
	private boolean status_footer = false;
	@SuppressWarnings("unused")
	private boolean status_footer_sec = false;

	private RelativeLayout prog_che;
	public ActionBar action;
	public int Status_Key_Enter_Down = 0;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		action =((AppCompatActivity) activity).getSupportActionBar();
		_activity = activity;
		_constant = new Constant(activity);
		_common = new CommonMethods(activity, this);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		_activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;

	}

	@SuppressWarnings("unused")
	@SuppressLint({ "Recycle", "NewApi" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.mainviewfragment, container,
				false);
		Bundle bundle = this.getArguments();

		str_direct = bundle.getString("direct");
		str_product_name_code = bundle.getString("code");
		Log.d("fag mai code ", "" + bundle.getString("code"));

		MyPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		LayoutInflater inflaterfooter = _activity.getLayoutInflater();
		productlistView = (ListView) rootView
				.findViewById(R.id.main_view_fragment_list_view);

		productgridview = (GridView) rootView
				.findViewById(R.id.main_view_grid_new);

		productFullList = (ListView) rootView
				.findViewById(R.id.main_view_fragment_full_custom_list_view_new);

		// /Footer Add Work

		// foot = LayoutInflater.from(_activity).inflate(R.layout.loading_foot,
		// null);
		// pb = (ProgressBar) foot.findViewById(R.id.progressbar_full_list_img);

		// footermsg = (TextView)
		// foot.findViewById(R.id.textView_footer_loading);

		LayoutInflater layoutInflater = LayoutInflater.from(_activity);

		// footerView = layoutInflater.inflate(R.layout.loading_foot, null);

		prog_che = (RelativeLayout) rootView.findViewById(R.id.footer_che);
		prog_che.setVisibility(View.GONE);

		allKindOfStatusCheck();

		// //// check condition for diract is "yes or no" /////

		if (str_direct.equals("yes")) {
			if (_constant.getConnectivityStatus()) {
				try {
					this.gsonProductDetail = null;
					_common.GetProductDetailByName(_common
							.getProductRequestByNameParmas(
									str_product_name_code, ""
											+ pageNumberForService, "", "",
									order), true);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			} else {
				_constant.showMsg(getResources().getString(
						R.string.internet_error_message));
			}

		} else {
			// popupSearchByname();
			showDialog(str_direct);
		}

		return rootView;
	}

	void listview() {
		productgridview.setVisibility(View.GONE);
		productFullList.setVisibility(View.GONE);
		productlistView.setVisibility(View.VISIBLE);
		Log.d("hello", "list view function");

		productlistView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				System.out.println("State Change is---" + scrollState);

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int i = firstVisibleItem + visibleItemCount;
				if ((i == totalItemCount) && (!loadingMore)) {
					if (pageNumberForService <= int_total_page) {
						Log.d("PAGE", "page No" + pageNumberForService
								+ " TOTAL" + int_total_page);
						loadingMore = true;

						// try {
						// productlistView.addFooterView(foot,null,false);
						// } catch (Exception e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// // }
						prog_che.setVisibility(View.VISIBLE);

						callSecondTimeWebServices(pageNumberForService);
					}
				}
			}

			private void callSecondTimeWebServices(int paramAnonymousInt) {
				if (_constant.getConnectivityStatus()) {

					if (gsonProductDetail != null) {

						try {
							_common.GetProductDetailByName(_common
									.getProductRequestByNameParmas(
											str_product_name_code, ""
													+ pageNumberForService, "",
											"", order), false);
						} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
							localUnsupportedEncodingException.printStackTrace();
						}

					}
				} else {
					_constant.showMsg(getActivity().getResources().getString(

					R.string.internet_error_message));
				}
			}
		});
	}

	void gridview() {
		Log.d("lol", "grid view call");
		productlistView.setVisibility(View.GONE);
		productFullList.setVisibility(View.GONE);
		productgridview.setVisibility(View.VISIBLE);

		productgridview.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				System.out.println("State Change is---" + scrollState);

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int i = firstVisibleItem + visibleItemCount;
				if ((i == totalItemCount) && (!loadingMore)) {
					if (pageNumberForService <= int_total_page) {
						Log.d("PAGE", "page No" + pageNumberForService
								+ " TOTAL" + int_total_page);
						loadingMore = true;

						// productgridview.addFooterView(foot);
						prog_che.setVisibility(View.VISIBLE);
						callSecondTimeWebServices(pageNumberForService);
					}

				}
			}

			private void callSecondTimeWebServices(int paramAnonymousInt) {
				if (_constant.getConnectivityStatus()) {
					if (gsonProductDetail != null) {
						try {

							_common.GetProductDetailByName(_common
									.getProductRequestByNameParmas(
											str_product_name_code, ""
													+ pageNumberForService, "",
											"", order), false);
						} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
							localUnsupportedEncodingException.printStackTrace();
						}
					}
				} else {
					_constant.showMsg(getActivity().getResources().getString(

					R.string.internet_error_message));
				}
			}
		});
	}

	void fulllistview() {
		productlistView.setVisibility(View.GONE);
		productFullList.setVisibility(View.VISIBLE);
		productgridview.setVisibility(View.GONE);

		productFullList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				System.out.println("State Change is---" + scrollState);

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int i = firstVisibleItem + visibleItemCount;
				if ((i == totalItemCount) && (!loadingMore)) {
					if (pageNumberForService <= int_total_page) {
						Log.d("PAGE", "page No" + pageNumberForService
								+ " TOTAL" + int_total_page);
						loadingMore = true;

						// /productFullList.addFooterView(foot,null,false);
						prog_che.setVisibility(View.VISIBLE);
						callSecondTimeWebServices(pageNumberForService);
					}
				}
			}

			private void callSecondTimeWebServices(int paramAnonymousInt) {
				if (_constant.getConnectivityStatus()) {
					if (gsonProductDetail != null) {

						try {

							_common.GetProductDetailByName(_common
									.getProductRequestByNameParmas(
											str_product_name_code, ""
													+ pageNumberForService, "",
											"", order), false);
						} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
							localUnsupportedEncodingException.printStackTrace();
						}
					}
				} else {
					_constant.showMsg(getActivity().getResources().getString(

					R.string.internet_error_message));
				}
			}

		});

	}

	// /Simple List Adapter
	class MyCustomAdap extends BaseAdapter {

		private LayoutInflater inflater;
		Activity _activity;
		private ArrayList<ProductDetailByName> al = new ArrayList<ProductDetailByName>();

		public MyCustomAdap(Activity act,
				ArrayList<ProductDetailByName> al_product_detail) {
			_activity = act;
			this.al = al_product_detail;
		}

		@Override
		public int getCount() {
			return al.size();
		}

		@Override
		public ProductDetailByName getItem(int position) {
			return al.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(final int position, View convertViewl1,
				ViewGroup parent) {
			viewHolder holder = null;

			if (convertViewl1 == null) {
				inflater = (LayoutInflater) getActivity().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);

				convertViewl1 = inflater.inflate(R.layout.temp, null);

				holder = new viewHolder();

				holder.list_icon_imageview = (ImageView) convertViewl1
						.findViewById(R.id.imageView_tv_titlle_story);

				holder.list_arrow_image = (ImageView) convertViewl1
						.findViewById(R.id.imageViewarrow);

				holder.list_mainTag_textview = (TextView) convertViewl1
						.findViewById(R.id.tv_title_Category);

				holder.list_category_textview = (TextView) convertViewl1
						.findViewById(R.id.textView_category);

				holder.list_price_textview = (TextView) convertViewl1
						.findViewById(R.id.textView_price);

				convertViewl1.setTag(holder);

			} else {
				holder = (viewHolder) convertViewl1.getTag();
			}

			if (!al.get(position).getThumb_image().equals("")
					|| al.get(position).getThumb_image() != null) {
				try {
					Picasso.with(getActivity())
							.load(al.get(position).getThumb_image())
							.placeholder(R.drawable.placeholder)
							.error(R.drawable.placeholder)
							.into(holder.list_icon_imageview);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			holder.list_category_textview.setText(al.get(position)
					.getCategory());
			holder.list_mainTag_textview.setText(al.get(position).getTitle());
			holder.list_price_textview.setText(al.get(position).getPrice()
					+ " ₹");

			convertViewl1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Log.d("MAHi", "" + al.get(position).getImages());
					ArrayList<String> temp_image_Array = al.get(position)
							.getImages();
					String asin = al.get(position).getAsin();
					OverViewClass.searchByName = true;
					Intent intentc = new Intent(MainViewFragment.this._activity.getApplicationContext(),
							peelyourdeal.best.price.compare.app.OverViewClass.class);
					intentc.putExtra("code", asin);
					intentc.putExtra("whichservice", "asin");
					intentc.putStringArrayListExtra("image_array",
							temp_image_Array);
//					intentc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					MainViewFragment.this._activity.startActivity(intentc);

					getActivity().overridePendingTransition(
							R.anim.slide_in_right, R.anim.slide_out_left);

				}
			});

			return convertViewl1;
		}

		class viewHolder {

			ImageView list_icon_imageview, list_arrow_image;
			TextView list_mainTag_textview, list_category_textview,
					list_price_textview;

		}

		void setData(ArrayList<ProductDetailByName> products) {
			this.al = products;
			notifyDataSetChanged();

		}
	}

	// Grid List Adapter

	class MygridAdapter_Adapt extends BaseAdapter {
		private LayoutInflater inflater;
		Activity _activity;

		private ArrayList<ProductDetailByName> al = new ArrayList<ProductDetailByName>();
		private int adapterHeight;
		private int adapterWidth;

		public MygridAdapter_Adapt(Activity act,
				ArrayList<ProductDetailByName> al_product_detail, int height,
				int width) {

			_activity = act;
			this.al = al_product_detail;
			this.adapterHeight = height / 5;
			this.adapterWidth = width / 2;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return al.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return al.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertViewlg,
				ViewGroup parent) {

			viewHolder holder = null;

			if (convertViewlg == null) {
				inflater = (LayoutInflater) getActivity().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);

				convertViewlg = inflater.inflate(R.layout.custom_grid_view, null);

				holder = new viewHolder();

				holder.lll = (LinearLayout) convertViewlg
						.findViewById(R.id.llimage);

				LayoutParams params = holder.lll.getLayoutParams();
				params.height = adapterHeight;
				params.width = adapterWidth;
				holder.lll.setLayoutParams(params);

				holder.Fram_icon_imageView = (ImageView) convertViewlg
						.findViewById(R.id.imageView1);
				holder.Fram_icon_imageView.setScaleType(ScaleType.FIT_CENTER);

				holder.Main_Tag_TextView = (TextView) convertViewlg
						.findViewById(R.id.textView_gid_title);

				holder.Category_textView = (TextView) convertViewlg
						.findViewById(R.id.textView_grid_category);

				holder.Price_category = (TextView) convertViewlg
						.findViewById(R.id.textView_grid_price);

				convertViewlg.setTag(holder);

			} else {
				holder = (viewHolder) convertViewlg.getTag();
			}
			if (!al.get(position).getThumb_image().equals("")
					|| al.get(position).getThumb_image() != null) {
				try {
					Picasso.with(getActivity())
							.load(al.get(position).getThumb_image())
							.placeholder(R.drawable.placeholder)
							.error(R.drawable.placeholder)
							.into(holder.Fram_icon_imageView);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			holder.Main_Tag_TextView.setText(al.get(position).getTitle());
			holder.Category_textView.setText(al.get(position).getCategory());
			holder.Price_category.setText(al.get(position).getPrice() + " ₹");

			convertViewlg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					ArrayList<String> temp_image_Array = al.get(position)
							.getImages();
					String asin = al.get(position).getAsin();
					OverViewClass.searchByName = true;
					Intent intentg = new Intent(MainViewFragment.this._activity.getApplicationContext(),
							peelyourdeal.best.price.compare.app.OverViewClass.class);
					intentg.putExtra("code", asin);
					intentg.putExtra("whichservice", "asin");
					intentg.putStringArrayListExtra("image_array",
							temp_image_Array);
//					intentg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					MainViewFragment.this._activity.startActivity(intentg);

					getActivity().overridePendingTransition(
							R.anim.slide_in_right, R.anim.slide_out_left);

				}
			});
			return convertViewlg;
		}

		public class viewHolder {
			LinearLayout lll;
			ImageView Fram_icon_imageView;
			TextView Main_Tag_TextView, Category_textView, Price_category;
		}

		public void setData(ArrayList<ProductDetailByName> products,
				int height, int width) {
			Log.d("TAG", "Set Data call");
			this.al = products;
			this.adapterHeight = height / 5;
			this.adapterWidth = width / 2;
			notifyDataSetChanged();

		}
	}

	// //Full List View Adapter

	public class MyFullAdapter_Adapt extends BaseAdapter {

		private LayoutInflater inflater;
		Activity _activity;
		private ArrayList<ProductDetailByName> al;
		private viewHolderFull holder;

		// private View vv;

		public MyFullAdapter_Adapt(Activity act,
				ArrayList<ProductDetailByName> al_product_detail) {
			_activity = act;
			this.al = al_product_detail;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return al.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return al.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertViewlf,
				ViewGroup parent) {

			inflater = (LayoutInflater) getActivity().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			if (convertViewlf == null) {
				holder = new viewHolderFull();
				convertViewlf = inflater.inflate(
						R.layout.custom_full_list_header, null);

				holder.Fram_icon_imageView_full = (ImageView) convertViewlf
						.findViewById(R.id.imageView_fulllist_icon);

				// holder.pb_full = (ProgressBar) convertViewlf
				// .findViewById(R.id.progressbar_full_list_img);

				holder.Main_f_Tag_TextView_full = (TextView) convertViewlf
						.findViewById(R.id.textView_main_full_tag_text);

				holder.Category_textView_full = (TextView) convertViewlf
						.findViewById(R.id.textView_full_category_tag);

				holder.Price_category_full = (TextView) convertViewlf
						.findViewById(R.id.textView_full_price_tag);

				convertViewlf.setTag(holder);

			} else {
				holder = (viewHolderFull) convertViewlf.getTag();
			}
			try {
				Picasso.with(_activity).load(al.get(position).getImage())
						.placeholder(R.drawable.placeholder)
						.error(R.drawable.placeholder)
						.into(holder.Fram_icon_imageView_full);
			} catch (Exception e) {

			}

			holder.Main_f_Tag_TextView_full
					.setText(al.get(position).getTitle());
			holder.Category_textView_full.setText(al.get(position)
					.getCategory());
			holder.Price_category_full.setText(al.get(position).getPrice()
					+ " ₹");

			convertViewlf.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d("Click", "On view");

					ArrayList<String> temp_image_Array = al.get(position)
							.getImages();
					Log.d("Click", "On view imag"+temp_image_Array);
					String asin = al.get(position).getAsin();
					OverViewClass.searchByName = true;
					Intent intentf = new Intent(MainViewFragment.this._activity.getApplicationContext(),
							peelyourdeal.best.price.compare.app.OverViewClass.class);
					Log.d("Click", "On view intent"+intentf);
					intentf.putExtra("code", asin);
					intentf.putExtra("whichservice", "asin");
					intentf.putStringArrayListExtra("image_array",
							temp_image_Array);
//					intentf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					intentf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
					MainViewFragment.this._activity.startActivity(intentf);
					Log.d("Click", "final intent call");
					getActivity().overridePendingTransition(
							R.anim.slide_in_right, R.anim.slide_out_left);

				}
			});

			return convertViewlf;
		}

		class viewHolderFull {
			// ProgressBar pb_full;
			ImageView Fram_icon_imageView_full;
			TextView Main_f_Tag_TextView_full, Category_textView_full,
					Price_category_full;

		}

		void setData(ArrayList<ProductDetailByName> products) {
			this.al = products;
			notifyDataSetChanged();

		}
	}

	public void showDialog(String msg) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
		builder.setIcon(R.drawable.bc_logo);
		builder.setTitle(getResources().getString(R.string.app_name));
		builder.setMessage("Barcode not found please enter the product name.");
		final EditText input = new EditText(_activity);
		input.setGravity(Gravity.CENTER_HORIZONTAL);
		// input.setFilters(new InputFilter[] { filter });
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		input.setBackgroundColor(Color.TRANSPARENT);
		input.setHint("Enter product name...");
		input.setMaxLines(1);
		input.setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE);
		input.setImeOptions(EditorInfo.IME_ACTION_DONE);
		input.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE ) {
//					InputMethodManager imm = (InputMethodManager) getSystemService(getAc.INPUT_METHOD_SERVICE);
//					imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
					
					strName_ = input.getText().toString();
					if (_constant.getConnectivityStatus()) {
						try {
							
							try {
								action.setTitle(strName_);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							MainActivity.title_activity = strName_;
							// getActivity().getActionBar().setTitle(strName);
							_common.GetProductDetailByName(_common
									.getProductRequestByNameParmas(strName_, ""
											+ pageNumberForService, "BARCODETITLE",
											str_product_name_code, order), true);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

					} else {
						_constant.showMsg(getResources().getString(
								R.string.internet_error_message));
					}
					
					return true;
				}
				return false;
			}
		});
		builder.setView(input); // uncomment this line
		builder.setCancelable(false);
		
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

				strName_ = input.getText().toString();
				if (_constant.getConnectivityStatus()) {
					try {
						
						try {
							action.setTitle(strName_);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MainActivity.title_activity = strName_;
						// getActivity().getActionBar().setTitle(strName);
						_common.GetProductDetailByName(_common
								.getProductRequestByNameParmas(strName_, ""
										+ pageNumberForService, "BARCODETITLE",
										str_product_name_code, order), true);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else {
					_constant.showMsg(getResources().getString(
							R.string.internet_error_message));
				}
			}
		});

		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent localIntent = new Intent(_activity,
								MainOptionActivity.class);
						_activity.startActivity(localIntent);
						_activity.overridePendingTransition(
								R.anim.slide_in_left, R.anim.slide_out_right);
						_activity.finish();
						dialog.dismiss();

					}
				});

		final AlertDialog dialog = builder.create();
		dialog.show();

		// Initially disable the button
		((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
				.setEnabled(false);
		// OR you can use here setOnShowListener to disable button at first
		// time.

		// Now set the textchange listener for edittext
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Check if edittext is empty
				if (TextUtils.isEmpty(s)) {
					// Disable ok button
					((AlertDialog) dialog).getButton(
							AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				} else {
					// Something into edit text. Enable the button.
					((AlertDialog) dialog).getButton(
							AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}
			}
		});
	}

	@Override
	public void OnComplete(APIResponse apiResponse) {

		System.out.println("Response of search by product name"
				+ apiResponse.getResponse());

		if (apiResponse.getCode() == 200) {
			
			Gson gson = new Gson();
			
			if (gsonProductDetail == null) {

				gsonProductDetail = gson.fromJson(apiResponse.getResponse(),
						GsonParseProduct.class);
				try {
					int_total_page = Integer.parseInt(gsonProductDetail
							.getTotalPages());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("MAHILOL", "" + gsonProductDetail.getStatus() + "msg:"
						+ gsonProductDetail.getMsg());
				if (gsonProductDetail.getStatus().equals("true")) {
					// prog_che.setVisibility(View.GONE);

					setNowAdapter();
					if (!st) {
						prog_che.setVisibility(View.GONE);
					}

					// (overViewAdapterSearchByName);
					if (pageNumberForService <= int_total_page) {
						pageNumberForService = (1 + pageNumberForService);
						statusprogressbarshow_footer = (1 + statusprogressbarshow_footer);

					}
					loadingMore = false;
				} 
				else {
					
					showDialog2(gsonProductDetail.getMsg());
					
				}

			} else {
				Log.d("null ni gai", "lol");
				GsonParseProduct localGsonParseProduct = gson.fromJson(
						apiResponse.getResponse(), GsonParseProduct.class);
				int_total_page = Integer.parseInt(localGsonParseProduct
						.getTotalPages());
				if (localGsonParseProduct.getStatus().equals("true")) {
					gsonProductDetail.getProducts().addAll(
							localGsonParseProduct.getProducts());

					if (temp_view.equals("0")) {
						myCustomAdap.setData(this.gsonProductDetail
								.getProducts());
						// productlistView.removeFooterView(foot);
						prog_che.setVisibility(View.GONE);
					} else if (temp_view.equals("1")) {
						Log.d("TAG", "grid set data");
						gridAdapter.setData(
								this.gsonProductDetail.getProducts(), height,
								width);
						// productgridview.removeFooterView(foot);
						prog_che.setVisibility(View.GONE);
					} else if (temp_view.equals("2")) {
						Log.d("TAG", "Full set data");
						fullAdapter.setData(this.gsonProductDetail
								.getProducts());
						// productFullList.removeFooterView(foot);
						prog_che.setVisibility(View.GONE);
					}
					// productlistView.addFooterView(null);
					loadingMore = false;

					// setNowAdapter();
				}

				if (pageNumberForService <= int_total_page) {
					pageNumberForService = (1 + pageNumberForService);
				}
			}

		} else {
			_constant.showMsg("Internet is too slow");
		}

	}

	public void showDialog2(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setCancelable(false);
		builder.setMessage(msg)
				.setTitle(getResources().getString(R.string.app_name))
				.setIcon(R.drawable.bc_logo)

				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						getActivity().onBackPressed();
						dialog.dismiss();
					}

				});

		AlertDialog dialog = builder.show();
		dialog.setCanceledOnTouchOutside(false);
		TextView messageText = (TextView) dialog
				.findViewById(android.R.id.message);
		messageText.setGravity(Gravity.CENTER);

	}

	@Override
	public void selectOnItem(boolean status) {

		st = status;
		if (status) {

			if (this.gsonProductDetail == null) {
				_constant.showMsg("Internet Not Working..");
			} else {

				allKindOfStatusCheck();
				setNowAdapter();
			}
		} else if (!status) {

			if (_constant.getConnectivityStatus()) {
				productlistView.setAdapter(null);
				productgridview.setAdapter(null);
				productFullList.setAdapter(null);
				pageNumberForService = 1;
				loadingMore = false;
				allKindOfStatusCheck();

				str_product_name_code = MyPrefs.getString(
						"product_code_search", "");
				try {

					this.gsonProductDetail = null;
					_common.GetProductDetailByName(_common
							.getProductRequestByNameParmas(
									str_product_name_code, ""
											+ pageNumberForService, "", "",
									order), true);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			} else {
				_constant.showMsg(getResources().getString(
						R.string.internet_error_message));
			}

		}
	}

	void allKindOfStatusCheck() {

		temp_view = MyPrefs.getString("view", "0");
		temp_click = MyPrefs.getString("click", "no");
		if (temp_click.equalsIgnoreCase("yes")) {
			prog_che.setVisibility(View.GONE);
			// CallGsonReq = false;
			if (temp_view.equalsIgnoreCase("0")) {
				gridview();
				status = 1;
				Log.d("click", "hua tha grid");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "1");
				edit.putString("click", "no");
				edit.commit();
				MyApplication.getInstance().trackScreenView(
						"Grid View Fragment");
			} else if (temp_view.equalsIgnoreCase("1")) {
				fulllistview();
				status = 2;
				Log.d("click", "full k ly");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "2");
				edit.putString("click", "no");
				edit.commit();
				MyApplication.getInstance().trackScreenView(
						"Full List Fragment");
			} else if (temp_view.equalsIgnoreCase("2")) {
				listview();
				status = 0;
				Log.d("click", "list k ly");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "0");
				edit.putString("click", "no");
				edit.commit();
				MyApplication.getInstance().trackScreenView(
						"List View Fragment");
			}
		} else {
			prog_che.setVisibility(View.GONE);
			if (temp_view.equalsIgnoreCase("0")) {
				listview();
				MyApplication.getInstance().trackScreenView(
						"List View Fragment");
				Log.d("hello", "list");
			} else if (temp_view.equalsIgnoreCase("1")) {
				gridview();
				MyApplication.getInstance().trackScreenView(
						"Grid View Fragment");
				Log.d("hello", "grid");
			} else if (temp_view.equalsIgnoreCase("2")) {
				fulllistview();
				MyApplication.getInstance().trackScreenView(
						"Full List Fragment");
				Log.d("hello", "grid");
			}
		}

	}

	void setNowAdapter() {
		tempo = MyPrefs.getString("view", "0");

		if (tempo.equals("0")) {

			myCustomAdap = new MyCustomAdap(_activity,
					gsonProductDetail.getProducts());

			productlistView.setAdapter(myCustomAdap);

		} else if (tempo.equals("1")) {

			gridAdapter = new MygridAdapter_Adapt(_activity,
					gsonProductDetail.getProducts(), height, width);
			productgridview.setAdapter(gridAdapter);

		} else if (tempo.equals("2")) {

			fullAdapter = new MyFullAdapter_Adapt(_activity,
					gsonProductDetail.getProducts());
			productFullList.setAdapter(fullAdapter);

		}
	}

	private InputFilter filter = new InputFilter() {

		@Override
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {

			if (source != null && blockCharacterSet.contains(("" + source))) {
				return "";
			}
			return null;
		}
	};

//	public void sendBroadcast() {
//		Log.d("send Broad", "cast call");
//		Intent intent = new Intent("send");
//		intent.putExtra("title_Activity", strName_);
//		LocalBroadcastManager.getInstance(getActivity()).sendBroadcastSync(
//				intent);
//		 LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//	}
}*/
