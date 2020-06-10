package vn.edu.ntu.trantiencao.myshoppingcart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

import vn.edu.ntu.trantiencao.controller.CartController;
import vn.edu.ntu.trantiencao.controller.ICartController;
import vn.edu.ntu.trantiencao.model.Product;

public class Fragment_list_product extends Fragment {
    RecyclerView rvListProduct;
    ProductAdapter productAdapter;
    List<Product> listProducts;
    FloatingActionButton btnAddProduct;
    NavController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        btnAddProduct = view.findViewById(R.id.btnAddProduct);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Fragment_list_product.this).navigate(R.id.action_ListProductFragment_to_fragment_add_product);
            }
        });

        initView();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        controller = NavHostFragment.findNavController(this);
//    ((MainActivity) getActivity()).controller = this.controller;
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_exit)  System.exit(0);
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        FragmentActivity activity = getActivity();
        rvListProduct = activity.findViewById(R.id.rvProducts);
        rvListProduct.setHasFixedSize(true);
        rvListProduct.setLayoutManager(new LinearLayoutManager(activity));
        CartController cartController = (CartController) activity.getApplication();
        listProducts = cartController.getProducts();
        productAdapter = new ProductAdapter(listProducts);
        rvListProduct.setAdapter(productAdapter);
        activity.findViewById(R.id.imgCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Fragment_list_product.this).navigate(R.id.action_ListProductFragment_to_Fragment_cart);
            }
        });
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtPrice, txtDesc;
        ImageView imgAddToCart;
        Product prodcut;

        private ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtPName);
            txtPrice = this.itemView.findViewById(R.id.txtPPrice);
            txtDesc = this.itemView.findViewById(R.id.txtPDesc);
            imgAddToCart = this.itemView.findViewById(R.id.imgAddCart);
            imgAddToCart.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            CartController controller = (CartController) getActivity().getApplication();
            if(controller.addToCart(prodcut)) {
                Toast.makeText(getActivity(), "Đã thêm " + prodcut.getName() + " vào giỏ hàng.",
                        Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(), prodcut.getName() + " đã có sẵn trong giỏ hàng.",
                        Toast.LENGTH_SHORT).show();
        }

        private void bind(Product product){
            txtName.setText(product.getName());
            txtPrice.setText(String.format(Locale.ENGLISH, "%d", product.getPrice()));
            txtDesc.setText(product.getDesc());
            imgAddToCart.setImageResource(product.getImg());
            this.prodcut = product;
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
        List<Product> listProduct;

        private ProductAdapter(List<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}