
CREATE USER clinlims PASSWORD '[% itechappPassword %]';
CREATE USER admin SUPERUSER PASSWORD '[% adminPassword %]';
CREATE DATABASE clinlims OWNER clinlims encoding 'UTF-8';
