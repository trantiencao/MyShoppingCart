package vn.edu.ntu.trantiencao.myshoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import vn.edu.ntu.trantiencao.controller.CartController;
import vn.edu.ntu.trantiencao.controller.ICartController;
import vn.edu.ntu.trantiencao.model.Product;

public class FirstFragment extends Fragment {
    RecyclerView rvListProduct;
    ProductAdapter productAdapter;
    List<Product> listProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.imgCart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

        initView();
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
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
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
            ICartController controller = (ICartController) getActivity().getApplication();
            if(controller.addToCart(prodcut)) {
                Toast.makeText(getActivity(), "Đã thêm " + prodcut.getName() + " vào giỏ hàng.",
                        Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(), prodcut.getName() + " đã có sẵn trong giỏ hàng.",
                        Toast.LENGTH_SHORT).show();
        }

        private void bind(Product product){
            this.prodcut = product;
            txtName.setText(product.getName());
            txtPrice.setText(String.format(Locale.ENGLISH, "%d", product.getPrice()));
            txtDesc.setText(product.getDesc());
            imgAddToCart.setImageResource(product.getImg());
        }

//        private void updateCart() {
//            SecondFragment fragment = (SecondFragment) getFragmentManager().findFragmentById(R.id.SecondFragment);
//            fragment.show();
//        }
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