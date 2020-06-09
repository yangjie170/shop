package com.shopmanage.entity;

public class OrderNumber {
        private int await_pay=0;
        private int await_ship=0;
        private int shipped=0;
        private int finished=0;
        public OrderNumber() {
        }
        public OrderNumber(int await_pay, int await_ship, int shipped, int finished) {
            this.await_pay = await_pay;
            this.await_ship = await_ship;
            this.shipped = shipped;
            this.finished = finished;
        }

        public void initNum(int await_pay, int await_ship, int shipped, int finished){
            this.await_pay = await_pay;
            this.await_ship = await_ship;
            this.shipped = shipped;
            this.finished = finished;
        }
        public int getAwait_pay() {
            return await_pay;
        }

        public void setAwait_pay(int await_pay) {
            this.await_pay = await_pay;
        }

        public int getAwait_ship() {
            return await_ship;
        }

        public void setAwait_ship(int await_ship) {
            this.await_ship = await_ship;
        }

        public int getShipped() {
            return shipped;
        }

        public void setShipped(int shipped) {
            this.shipped = shipped;
        }

        public int getFinished() {
            return finished;
        }

        public void setFinished(int finished) {
            this.finished = finished;
        }

}
