package com.before.subject;

import com.before.observers.IObserver;

interface ISubject {

    void registerObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();

}

