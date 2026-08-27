package zeev.fraiman.assemblethemolecule.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zeev.fraiman.assemblethemolecule.MoleculeModelActivity;
import zeev.fraiman.assemblethemolecule.R;
import zeev.fraiman.assemblethemolecule.model.Substance;
import zeev.fraiman.assemblethemolecule.utils.FormulaUtils;

public class SubstanceAdapter extends RecyclerView.Adapter<SubstanceAdapter.SubstanceViewHolder> {

    private final List<Substance> substances;

    public SubstanceAdapter(List<Substance> substances) {
        this.substances = substances;
    }

    @NonNull
    @Override
    public SubstanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_substance, parent, false);
        return new SubstanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubstanceViewHolder holder, int position) {
        Substance substance = substances.get(position);
        holder.tvFormula.setText(FormulaUtils.formatForDisplay(substance.getFormula()));
        holder.tvName.setText(substance.getName());
        holder.tvClass.setText(substance.getClassName());

        holder.btnShowModel.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MoleculeModelActivity.class);
            intent.putExtra("formula", substance.getFormula());
            intent.putExtra("name", substance.getName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return substances.size();
    }

    static class SubstanceViewHolder extends RecyclerView.ViewHolder {
        TextView tvFormula, tvName, tvClass;
        Button btnShowModel;

        public SubstanceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFormula = itemView.findViewById(R.id.tvFormula);
            tvName = itemView.findViewById(R.id.tvName);
            tvClass = itemView.findViewById(R.id.tvClass);
            btnShowModel = itemView.findViewById(R.id.btnShowModel);
        }
    }
}
