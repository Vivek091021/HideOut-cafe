package com.example.hideoutcafe;

public class AddButton {

//    MaterialButton add;
//
//    LinearLayout inc;
//
//    TextView t1, t2, t3;
//
//    Context context;
//    View view;
//
//    public AddButton() {
//    }
//    public AddButton(Context context,View view) {
//        this.context = context;
//        this.view = view;
//    }
//
//
//
//        add = findViewById(R.id.add);
//        inc = findViewById(R.id.inc);
//        t1 = findViewById(R.id.t1);
//        t2 = findViewById(R.id.t2);
//        t3 = findViewById(R.id.t3);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(add.getText().toString().equals("Add")){
//                    add.setVisibility(View.GONE);
//                    inc.setVisibility(View.VISIBLE);
//                } else {
//                    inc.setVisibility(View.GONE);
//                    add.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inc(false);
//            }
//        });
//
//        t3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inc(true);
//            }
//        });
//
//
//    private void inc(Boolean x){
//        int y = Integer.parseInt(t2.getText().toString());
//        if(x){
//            y++;
//            t2.setText(String.valueOf(y));
//        }else {
//            y--;
//            if(y == 0){
//                inc.setVisibility(View.GONE);
//                add.setVisibility(View.VISIBLE);
//            }else {
//                t2.setText(String.valueOf(y));
//            }
//        }
//
//        Toast.makeText(this, t2.getText(), Toast.LENGTH_SHORT).show();
//
//    }
}
