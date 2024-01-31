package com.before.observers;


import com.before.domain.Employee;

/**
 * This interface defines the method that the subject will call when it
 * wants to notify its list of observers. This is actually the callback method
 * from what I understand.
 *
 * @author Screencasts
 */
public interface IObserver {
    // When something changes in the subject it should call this method..
    void callMe(Employee emp, String msg);
}
