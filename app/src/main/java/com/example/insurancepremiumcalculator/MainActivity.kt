package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
    //(if 2nd selected item depends on 1st，then only use this),else use listener only
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       //val selectedItem =spinnerAge.selectedItemPosition    same
        val selectedItem =spinnerAge.getItemAtPosition(position)
        Toast.makeText(this,"Selected Item="+selectedItem,Toast.LENGTH_LONG).show()
    }

/*override  fun onClick(v: View?){
    when(v){
        buttonCalculate->calculatePremium()
        buttonReset-> resetOutput()
        else->
    }
}*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        spinnerAge.onItemSelectedListener=this
       /* val buttonThird:Button
        buttonThird.setOnClickLister(this)*/

    }
    private fun calculatePremium() {

        val age :Int=spinnerAge.selectedItemPosition
        var premium = when(age){
            0->60
            1->70
            2->90
            3->120
            else->150
        }
        val gender:Int=radioGroupGender.checkedRadioButtonId
        var genderExtra=0
        if(gender==R.id.radioButtonMale){
             genderExtra = when(age){
                0->0
                1->50
                2->100
                3->150
                else->200
            }
        }
        val smoker:Boolean=checkBoxSmoker.isChecked
        var smokeExtra=0
        if(smoker==true){
            smokeExtra = when(age){
                0->0
                1->100
                2->150
                3->200
                4->250
                else->300
            }
        }
        var total=premium+genderExtra+smokeExtra
        textViewPremium.setText(R.string.insurance_premium + R.string.rm + total)
    }
    fun reset(view: View) {
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        checkBoxSmoker.isChecked=false
    }

}
