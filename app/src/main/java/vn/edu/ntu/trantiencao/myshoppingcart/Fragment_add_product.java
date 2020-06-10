package vn.edu.ntu.trantiencao.myshoppingcart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.trantiencao.controller.CartController;
import vn.edu.ntu.trantiencao.model.Product;

public class Fragment_add_product extends Fragment {
    EditText etxtAddNameProduct, etxtAddPriceProduct, etxtAddDescProduct;
    List<Product> dsProduct;
    NavController controller;
    Button btnActionAddProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_product, container, false);
        btnActionAddProduct = view.findViewById(R.id.btnActionAddProduct);
        return view;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        controller   = NavHostFragment.findNavController(this);
//        ((MainActivity) getActivity()).controller = this.controller;
//    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        initView();
        btnActionAddProduct.findViewById(R.id.btnActionAddProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsProduct.add(new Product(String.valueOf(etxtAddNameProduct.getText()),
                        Integer.parseInt(String.valueOf(etxtAddPriceProduct.getText())),
                        String.valueOf(etxtAddDescProduct.getText()),R.id.imgAddCart));
                etxtAddNameProduct.setText("");
                etxtAddPriceProduct.setText("");
                etxtAddDescProduct.setText("");
                Toast.makeText(getActivity(),"Đã thêm sản phẩm vào danh sách sản phẩm",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        FragmentActivity activity = getActivity();
        etxtAddNameProduct = activity.findViewById(R.id.inputNameProduct);
        etxtAddPriceProduct = activity.findViewById(R.id.inputPriceProduct);
        etxtAddDescProduct = activity.findViewById(R.id.inputDescProduct);
        CartController controller = (CartController) getActivity().getApplication();
        dsProduct = controller.getProducts();
        activity.findViewById(R.id.imgCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Đến màn hình danh sách sản phẩm để vào giỏ hàng!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}