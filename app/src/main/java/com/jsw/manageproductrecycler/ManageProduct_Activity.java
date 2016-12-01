package com.jsw.manageproductrecycler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jsw.manageproductrecycler.Adapter.ProductAdapter;
import com.jsw.manageproductrecycler.Adapter.ReciclerAdapter;
import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Settings.AccountSettings;
import com.jsw.manageproductrecycler.Settings.GeneralSettings;
import com.jsw.manageproductrecycler.interfaces.IProducto;

import java.io.Serializable;

/*Cuando hacemos que herede de ListActivity Internamente ya tiene un tipo definido que es la lista
*
* Para que android haga la vinculacion del xml con nuestra lista interna, es decir
* que lo infle de forma automatica, tenemos que añadir al listview el id "android:id/list"*/

//no es necesario llamar al notifyDataSetChande despues de add, insert, remove,clear y sort
// porque estos metodos lo llama automaticamente setNotyfiOnChange = true y se utiliza la copia local.
public class ManageProduct_Activity extends AppCompatActivity implements IProducto {

  //  private ReciclerAdapter mAdapter; //Adapter
    //private RecyclerView mReciclerView; //Recycler View

    private ListView listProduct;
    private FloatingActionButton fabAdd;
    private Button btnctmdelete;

    private ProductAdapter adapterP;

    public static String PRODUCKKEY=PRODUCT_KEY;

    private static final int EDIT_PRODUCT = 1;
    private static final int ADD_PRODUCT = 0;

    private Intent intent;
    boolean sorted = false;

    Product paux;
    int posProd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
       // mAdapter = new ReciclerAdapter(this); //Add the adapter

        adapterP= new ProductAdapter(this);
        listProduct=(ListView)findViewById(R.id.lisVlistItems);
        listProduct.setAdapter(adapterP);
        fabAdd=(FloatingActionButton)findViewById(R.id.AP_fab_añadir);

       // btnctmdelete=(Button)findViewById(R.id.btn_delete_ctm);

        registerForContextMenu(listProduct);//AL implementar el registrer no es necesario el onLongClickListener

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                paux=(Product)parent.getItemAtPosition(position);
                posProd=position;

                Bundle bundle= new Bundle();
                bundle.putParcelable(PRODUCKKEY, paux);
                Intent intent= new Intent(ManageProduct_Activity.this,AddProduct_Activity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,EDIT_PRODUCT);

            }
        });

        //no es necesario porque esta el registerForContextMenu(listProduct)
        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posProd=i;
                return false;
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);
            }
        });
       // mReciclerView = (RecyclerView)findViewById(R.id.rv_vista); //View instance
        //mReciclerView.setLayoutManager(new LinearLayoutManager(this)); //Set the layout manager with a linearlayout
       // mReciclerView.setAdapter(mAdapter); //Add the adapter with the view
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //contiene info de la vista donde se muestra el menu contextal
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        posProd=info.position;

        if (item.getItemId()== R.id.btn_delete_ctm) {

            new AlertDialog.Builder(ManageProduct_Activity.this).setTitle(R.string.alertDialog_title)
                    .setMessage(R.string.alertDialog_sure)
                    .setPositiveButton(R.string.alertDialog_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           adapterP.removeProduct((Product) listProduct.getItemAtPosition(posProd));
                        }
                    })
                    .setNegativeButton(R.string.alertDialog_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();


        }
         return super.onContextItemSelected(item);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu_items, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
                intent = new Intent(this, AccountSettings.class);
                startActivity(intent);
                break;
            case R.id.action_general_settings:
                intent = new Intent(this, GeneralSettings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Floating Add Button actions.
     * It opens the AddProduct Activity
     * @param v Button View
     */
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

        }

    /**
     * Floating Add Button actions.
     * It opens the AddProduct Activity
     * @param v Button View
     */
    public void add(View v){
        intent = new Intent(ManageProduct_Activity.this, AddProduct_Activity.class);
        startActivityForResult(intent, 0);
    }

}
