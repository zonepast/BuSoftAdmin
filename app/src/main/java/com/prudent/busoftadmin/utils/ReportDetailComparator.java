package com.prudent.busoftadmin.utils;

import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.Table;

import java.util.Comparator;

/**
 * Created by Dharmik Patel on 13-Jun-17.
 */

public class ReportDetailComparator {

    private ReportDetailComparator() {
        //no instance
    }

    public static Comparator<Table> getAccountComparator() {
        return new AccountComparator();
    }

    private static class AccountComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            //return car1.getAccount().compareTo(car2.getAccount());
            return car1.getAccount().compareToIgnoreCase(car2.getAccount());
        }
    }

    public static Comparator<Table> getOpeningComparator() {
        return new OpeningComparator();
    }

    private static class OpeningComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            return car1.getOpening().compareTo(car2.getOpening());
        }
    }

    public static Comparator<Table> getCreditComparator() {
        return new CreditComparator();
    }

    private static class CreditComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            return car1.getCredit().compareTo(car2.getCredit());
        }
    }

    public static Comparator<Table> getDebitComparator() {
        return new DebitComparator();
    }

    private static class DebitComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            return car1.getDebit().compareTo(car2.getDebit());
        }
    }

    public static Comparator<Table> getClosingComparator() {
        return new ClosingComparator();
    }

    private static class ClosingComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            return car1.getClosing().compareTo(car2.getClosing());
        }
    }

    public static Comparator<Table> getChangeComparator() {
        return new ChangeComparator();
    }

    private static class ChangeComparator implements Comparator<Table> {

        @Override
        public int compare(final Table car1, final Table car2) {
            return car1.getChange().compareTo(car2.getChange());
        }
    }
}
