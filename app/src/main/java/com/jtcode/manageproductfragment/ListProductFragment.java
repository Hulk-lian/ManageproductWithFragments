package com.jtcode.manageproductfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jtcode.manageproductfragment.Adapter.ProductAdapter;
import com.jtcode.manageproductfragment.Model.Product;
import com.jtcode.manageproductfragment.Settings.AccountSettings;
import com.jtcode.manageproductfragment.Settings.GeneralSettings;
import com.jtcode.manageproductfragment.interfaces.IProducto;
import com.jtcode.manageproductfragment.presenter.ProductPresenter;
import com.jtcode.manageproductfragment.presenter.ProductPresenterImpl;

import java.util.List;

/*Cuando hacemos que herede de ListActivity Internamente ya tiene un tipo definido que es la lista
*
* Para que android haga la vinculacion del xml con nuestra lista interna, es decir
* que lo infle de forma automatica, tenemos que añadir al listview el id "android:id/list"*/

//no es necesario llamar al notifyDataSetChande despues de add, insert, remove,clear y sort
// porque estos metodos lo llama automaticamente setNotyfiOnChange = true y se utiliza la copia local.
public class ListProductFragment extends Fragment implements IProducto, ProductPresenter.View {

  //  private ReciclerAdapter mAdapter; //Adapter
    //private RecyclerView mReciclerView; //Recycler View

    private ListView listProduct;
    private FloatingActionButton fabAdd;
    private TextView txvNodata;


    private ProductAdapter adapterP;

    private Intent intent;
    boolean sorted = false;

    private ProductPresenter presenter;

    Product paux;
    int posProd;


    //objecto que  comunica la activity con el listproductListener
    private ListProductListener mCallback;

    interface ListProductListener{
        void showManageProduct(Bundle bundle);//lanza el fragment manageproduct
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterP= new ProductAdapter(getContext());
        presenter= new ProductPresenterImpl(this);

        setRetainInstance(true);
        /*
        Esta opcion le dice a la activity que el fragment tiene su propio menu y llama al
        metodo callback onCreateOptionMenu().
         */
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback=(ListProductListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(getContext().toString() +" must implement ListProductListener");
        }
    }


    //desinicializar todo lo que se inicializa en el onAttach
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback=null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView=inflater.inflate(R.layout.fragment_list_product,container,false);
        listProduct=(ListView)rootView.findViewById(R.id.lisVlistItems);
        txvNodata=(TextView)rootView.findViewById(android.R.id.empty);

        listProduct.setAdapter(adapterP);

        fabAdd=(FloatingActionButton)rootView.findViewById(R.id.AP_fab_añadir);

        registerForContextMenu(listProduct);//AL implementar el registrer no es necesario el onLongClickListener

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                paux=(Product)parent.getItemAtPosition(position);
                posProd=position;

                Bundle bundle= new Bundle();
                bundle.putParcelable(IProducto.PRODUCT_KEY, paux);
                mCallback.showManageProduct(bundle);

            }
        });

        //no es necesario porque esta el registerForContextMenu(listProduct)
        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posProd=i;
                //mostrar el dialog y ver la opcion que elige

                return false;
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showManageProduct(null);
            }
        });


        return rootView;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.context_menu_items, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_listproduct,menu);
    }

    /**
     * Si devolvemos true es que el evento lo hemos consumido, si devolvemos false, no hemos consumido el evento,
     * es decir he recibido el evento, he realizado operaciones y no quiero que se propague para arriba.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort_product:
                //mAdapter.sortProducts();
                break;
            case R.id.action_account_settings:
                intent = new Intent(getContext(), AccountSettings.class);
                startActivity(intent);
                break;
            case R.id.action_general_settings:
                intent = new Intent(getContext(), GeneralSettings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapterP=null;
        presenter=null;
    }

    private void hideList(boolean hide){
        listProduct.setVisibility(hide?View.GONE:View.VISIBLE);
        txvNodata.setVisibility(hide?View.VISIBLE:View.GONE);
    }

    public void showProducts(List<Product> products){
        adapterP.updateProduct(products);
    }
    //muesrta u oculta la lista
    public void showEmptyText(boolean show) {
        hideList(!show);
    }
    public void showMessage(String message){

    }

   /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ADD_PRODUCT:
                if(resultCode == RESULT_OK) {
                    Product product = data.getParcelableExtra(PRODUCT_KEY);
                    ((ProductAdapter) listProduct.getAdapter()).addProduct(product);
                }
                break;

            case EDIT_PRODUCT:
                if(resultCode == RESULT_OK){
                    Product product = data.getParcelableExtra(PRODUCT_KEY);
                    ((ProductAdapter) listProduct.getAdapter()).addAtPos(paux,product,posProd);
                }

                break;
        }
           // mAdapter.addProduct(p);
            //mAdapter.notifyDataSetChanged();

        }*/
}
