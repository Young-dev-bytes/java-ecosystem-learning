package com.example1.subject;

import com.example1.observers.IObserver;

interface ISubject {

    void registerObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();

}

