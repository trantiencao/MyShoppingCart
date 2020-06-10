package vn.edu.ntu.trantiencao.myshoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import vn.edu.ntu.trantiencao.controller.ICartController;
import vn.edu.ntu.trantiencao.model.Product;

public class Fragment_cart extends Fragment implements View.OnClickListener {
    TextView txtShopping;
    Button btnBuy, btnRemove;
    int length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        controller = NavHostFragment.findNavController(this);
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) System.exit(0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBuy:
                if(length > 0) {
                    clear();
                    Toast.makeText(getActivity(), "Cảm ơn bạn đã mua hàng!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRemove:
                clear();
                Toast.makeText(getActivity(), "Giỏ hàng của bạn đã bị xóa!", Toast.LENGTH_SHORT).show();
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
        activity.findViewById(R.id.imgCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Bạn đang ở trong giỏ hàng!",Toast.LENGTH_SHORT).show();
            }
        });
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