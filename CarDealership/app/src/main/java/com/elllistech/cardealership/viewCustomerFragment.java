package com.elllistech.cardealership;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link viewCustomerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link viewCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewCustomerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String customerResults = "";

    private OnFragmentInteractionListener mListener;

    public viewCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewCustomerFragment.
     */
    public static viewCustomerFragment newInstance(String param1, String param2) {
        viewCustomerFragment fragment = new viewCustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_customer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            Context context = getActivity();
            TextView
                    displayCustomers = (TextView)view.findViewById(R.id.displayCustomers);
            CarDealershipDBHelper
                    db = new CarDealershipDBHelper(context);
            List<Customer> customerList = db.getAllCustomers();

            for (Customer customer : customerList) {
                customerResults = customerResults + "Id: " + customer.getCustomerID() +
                        "\t\tFirst Name: " + customer.getFirstName() + "\t\tLast Name: " +
                        customer.getLastName() + "\nCar Make:" + customer.getCarMake()
                        + "\t\tCar Cost: " + customer.getCarCost() + "0" + "\n\n";
            }
            displayCustomers.setText(customerResults);
        } catch (SQLiteException e) {

        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
