package vn.edu.ntu.trantiencao.myshoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import vn.edu.ntu.trantiencao.controller.ICartController;
import vn.edu.ntu.trantiencao.model.Product;

public class SecondFragment extends Fragment implements View.OnClickListener {
    TextView txtShopping;
    Button btnBuy, btnRemove;
    int length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Move back to First Fragment", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        initViews();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBuy:
                if(length > 0) {
                    clear();
                    Toast.makeText(getActivity(), "Thank you for your purchases!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRemove:
                clear();
                Toast.makeText(getActivity(), "Your cart removed!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void clear() {
        FragmentActivity activity = getActivity();
        ICartController controller = (ICartController) activity.getApplication();
        controller.clearShoppingCart();
        txtShopping.setText("Empty");
        this.length = 0;
    }

    private void initViews() {
        FragmentActivity activity = getActivity();
        txtShopping = activity.findViewById(R.id.txtCart);
        btnBuy = activity.findViewById(R.id.btnBuy);
        btnRemove = activity.findViewById(R.id.btnRemove);
        btnBuy.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        showShoppingCart();
    }

    private void showShoppingCart() {
        FragmentActivity activity = getActivity();
        ICartController controller = (ICartController) activity.getApplication();
        List<Product> products = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for (Product p : products) {
            builder.append(p.getName())
                    .append(": ")
                    .append(p.getPrice())
                    .append("\n");
        }
        if(builder.length() > 0) {
            txtShopping.setText(builder.toString());
            this.length = controller.getShoppingCart().size();
        }
        else
            txtShopping.setText("Empty");
    }
}