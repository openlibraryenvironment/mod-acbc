package org.olf.acbc

class SharedIndexService {

  // We might someday share code for this with mod-rs once we decide how to do that, though
  // for the FOLIO-based SI there is a key difference that mod-rs is hitting it externally
  def getInstances(String id) {
    return [
      [symbol: 'id', id:'123'],
      [symbol: 'id', id:'123'],
      [symbol: 'kint', id:'123'],
      [symbol: 'kware', id:'123']
    ]
  }

}
