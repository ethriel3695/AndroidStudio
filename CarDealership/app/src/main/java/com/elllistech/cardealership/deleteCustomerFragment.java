package com.elllistech.cardealership;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link deleteCustomerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link deleteCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteCustomerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList
            customerResults = new ArrayList();
    private OnFragmentInteractionListener mListener;

    public deleteCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment deleteCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static deleteCustomerFragment newInstance(String param1, String param2) {
        deleteCustomerFragment fragment = new deleteCustomerFragment();
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
        return inflater.inflate(R.layout.fragment_delete_customer, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            final Context context = getActivity();
            TextView
                    displayCustomers = (TextView)view.findViewById(R.id.displayCustomers);
            final Spinner
                    customerIDs = (Spinner)view.findViewById(R.id.customerIDs);
            final CarDealershipDBHelper
                    db = new CarDealershipDBHelper(context);
            List<Customer> customerList = db.getAllCustomers();

            for (Customer customer : customerList) {
                customerResults.add(customer.getCustomerID());
            }

            ArrayAdapter<String>
                customerAdapter =
            new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, customerResults);
            customerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            customerIDs.setAdapter(customerAdapter);

            final Button
                    deleteCustomer = (Button)view.findViewById(R.id.btnDeleteCustomer);

            deleteCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String
                            customerIDSelected = customerIDs.getSelectedItem().toString();

                    Customer
                            customers = new Customer();
                    customers.setCustomerID(Long.parseLong(customerIDSelected));
                    db.deleteCustomer(customers);
                    Toast.makeText(context,
                            "Customer deleted!",
                            Toast.LENGTH_SHORT).show();

                    List<Customer> customerList = db.getAllCustomers();
                    customerResults = new ArrayList();

                    for (Customer customer : customerList) {
                        customerResults.add(customer.getCustomerID());
                    }

                    ArrayAdapter<String>
                            customerAdapter =
                            new ArrayAdapter<String>(context,
                                    android.R.layout.simple_spinner_item, customerResults);
                    customerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    customerIDs.setAdapter(customerAdapter);
                }
            });
        } catch (SQLiteException e) {

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
