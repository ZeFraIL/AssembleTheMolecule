package zeev.fraiman.assemblethemolecule.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zeev.fraiman.assemblethemolecule.R;
import zeev.fraiman.assemblethemolecule.model.Element;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementViewHolder> {

    private final List<Element> elements;
    private final OnElementClickListener listener;

    public interface OnElementClickListener {
        void onElementClick(Element element);
    }

    public ElementAdapter(List<Element> elements, OnElementClickListener listener) {
        this.elements = elements;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_element, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
        Element element = elements.get(position);
        holder.tvSymbol.setText(element.getSymbol());
        holder.tvAtomicNumber.setText(String.valueOf(element.getAtomicNumber()));
        holder.itemView.setOnClickListener(v -> listener.onElementClick(element));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    static class ElementViewHolder extends RecyclerView.ViewHolder {
        TextView tvSymbol, tvAtomicNumber;

        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvAtomicNumber = itemView.findViewById(R.id.tvAtomicNumber);
        }
    }
}
