package products.state;

public enum StateType {
    /**
     *Product is being produced by Processor
     **/
    InProcessType,

    /**
     *Product is being stored by a party
     **/
    StoredType,

    /**
     *Product is in transition from one party to another
     **/
    InTransitionType,

    /**
     *Product has expired
     **/
    SoldType
}
