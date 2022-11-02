class ImportApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ImportApi();
        }
        return this.#instance;
    }

    IMP = null;

    #importPayParams = {
        pg: "kakaopay",
        pay_method: "card",
        merchant_uid: "product-" + new Date().getTime(),
        name: "노르웨이 회전 의자",
        amount: 1,
        buyer_email: "gildong@gmail.com",
        buyer_name: "홍길동",
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    };

    impInfo = {
        impUid: null,
        restApiKey: null,
        restApiSecret: null
    }


    constructor() {
        this.IMP = window.IMP;
        this.impInfo.impUid = "imp54862214"; 
        this.impInfo.restApiKey = "1227562045920810";
        this.impInfo.restApiSecret = "532fe9c02ccc3d2ae2541c6f6d3b01939314c4c62d6149db1ec2155a04b366e0971580e991eb9d8a";

        this.IMP.init(this.impInfo.impUid); 
    }

    requestPay() {
        this.IMP.request_pay(this.#importPayParams, this.responsePay);
    }

    responsePay(resp) {
        if(resp.success) {
            alert("결제 성공!");
        }else {
            alert("결제 실패!");
        }
    }

}

class Order {

    constructor() {
        this.addPaymentButtonEvent();
    }

    addPaymentButtonEvent() {
        const paymentButton = document.querySelector(".payment-button");
        paymentButton.onclick = () => {
            ImportApi.getInstance().requestPay();
        } 
    }
}

window.onload = () => {
    AddressApi.getInstance().addAddressButtonEvent();
    new Order();
}