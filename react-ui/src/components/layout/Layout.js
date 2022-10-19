import React from 'react'
import Header from './Header'
import Footer from './Footer'

export default function Layout(props) {
  const { children } = props
  return (
    <>
      <div className="d-flex flex-column min-vh-100">
      <Header config={props.config} onChangeLanguage={props.onChangeLanguage} isLoggedIn={props.isLoggedIn} logout={props.logout}  />
      {children}
      <Footer/>
      </div>
    </>
  )
}