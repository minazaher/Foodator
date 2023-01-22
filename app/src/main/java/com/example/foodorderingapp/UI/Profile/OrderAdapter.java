package com.example.foodorderingapp.UI.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Data.OrderDao;
import com.example.foodorderingapp.Model.Order;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Data.ApplicationClass;
import com.example.foodorderingapp.Data.DishDatabase;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private final OrderDao dao = DishDatabase.instance.orderDao();

    List<Order> orders = dao.getUserOrders(ApplicationClass.currentUser.getID());

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details, parent, false);
        return new OrderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tv_orderID.setText(String.valueOf(orders.get(position).getOrderID()));
        holder.tv_orderDetails.setText(orders.get(position).getDishes());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    protected class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tv_orderID, tv_orderDetails;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_orderID = itemView.findViewById(R.id.tv_orderID);
            tv_orderDetails = itemView.findViewById(R.id.tv_orderDetails);
        }
    }
}
