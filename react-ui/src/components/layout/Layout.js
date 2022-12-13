import React from 'react'
import Header from './Header'
import Footer from './Footer'
import {
  Content, 
  Theme
} from "@carbon/react";

export default function Layout(props) {
  const { children } = props
  return (
    <>
      <div className="d-flex flex-column min-vh-100">
      <Header user={props.user} config={props.config} onChangeLanguage={props.onChangeLanguage} isLoggedIn={props.isLoggedIn} logout={props.logout}  />
      <Theme  theme="white">
        <Content>
          {children}
        </ Content>
        </Theme>
      <Footer/>
      </div>
    </>
  )
}