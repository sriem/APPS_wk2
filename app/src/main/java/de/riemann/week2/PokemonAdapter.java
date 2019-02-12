package de.riemann.week2;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.VH> {

    //inner ViewHolderClass
    class VH extends RecyclerView.ViewHolder {

        SimpleDraweeView pokemonImage;
        TextView pokemonName;
        TextView pokemonWeight;
        CheckBox pokemonCheckBox;


        public VH(View itemView) {
            super(itemView);
            pokemonCheckBox = itemView.findViewById(R.id.pokemonCheckBox);
            pokemonImage = (SimpleDraweeView) itemView.findViewById(R.id.pokemonImageView);
            pokemonName = itemView.findViewById(R.id.pokemonNameTextView);
            pokemonWeight = itemView.findViewById(R.id.pokemonWeightTextView);


        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, null);

        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        /*
         * https://frescolib.org/docs/
         * Imagehandler
         * */
        Uri uri = Uri.parse(MainActivity.pokemonImage.get(i));
        final View thisView = vh.itemView;
        vh.pokemonImage.setImageURI(uri);
        vh.pokemonName.setText(MainActivity.pokemonName.get(i));
        vh.pokemonWeight.setText(MainActivity.pokemonWeight.get(i));


        thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no function yet
            }
        });

        //Checkboxcontroll
        vh.pokemonCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) thisView.setBackgroundColor(0xFF96CAF0);
                else
                    thisView.setBackgroundColor(0xFFFFFFFF);

            }
        });

    }

    @Override
    public int getItemCount() {
        return MainActivity.pokemonName.size();
    }




}
